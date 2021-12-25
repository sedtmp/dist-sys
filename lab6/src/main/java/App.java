import actors.StoreActor;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import constants.Constants;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class App {
    public static void print(String msg) {
        System.out.println(Constants.GREEN + msg + Constants.RESET);
    }

    public static void main(String[] args) throws IOException {
        BasicConfigurator.configure();
        ActorSystem actorSystem = ActorSystem.create("routes");
        ActorRef actoreStorage = actorSystem.actorOf(Props.create(StoreActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(actorSystem);
        final Http http = Http.get(actorSystem);

        ZooKeeper zoo = null;
        try {
            zoo = new ZooKeeper(args[Constants.ZOOKEEPER_ADDRESS_INDEX], Constants.ZOOKEEPER_TIMEOUT, null);
            new ZooWatcher(zoo, actoreStorage);
        } catch (KeeperException | InterruptedException exception) {
            exception.printStackTrace();
            System.exit(-1);
        }

        CompletionStage<ServerBinding> binding = null;
        StringBuilder serversInfo = new StringBuilder("Servers online at\n");
        try {
            Server server = new Server(http, actoreStorage, zoo, args[Constants.PORT_INDEX]);
            final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = server.createRoute().flow(
                    actorSystem,
                    materializer
            );
            binding = http.bindAndHandle(
                    routeFlow,
                    ConnectHttp.toHost(Constants.HOST, Integer.parseInt(args[Constants.PORT_INDEX])),
                    materializer
            );
            serversInfo.append(Constants.createServerUrl(args[Constants.PORT_INDEX]));
        } catch (InterruptedException | KeeperException exception) {
            exception.printStackTrace();
        }

        if (binding == null) {
            System.out.println(Constants.ERROR);
        }
        print(serversInfo + "\nPress RETURN to stop...");

        try {
            System.in.read();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(-1);
        }

        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> actorSystem.terminate());
    }
}
