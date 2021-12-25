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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class App {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        BasicConfigurator.configure();
        ActorSystem actorSystem = ActorSystem.create("routes");
        ActorRef storage = actorSystem.actorOf(Props.create(StoreActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(actorSystem);
        final Http http = Http.get(actorSystem);

        ZooKeeper zoo = null;
        try {
            zoo = new ZooKeeper(args[Constants.ZOOKEEPER_ADDRESS_INDEX], Constants.ZOOKEEPER_TIMEOUT, null);
            new ZooWatcher(zoo, storage);
        } catch (KeeperException | InterruptedException exception) {
            exception.printStackTrace();
            System.exit(-1);
        }

        List<CompletionStage<ServerBinding>> bindings = new ArrayList<>();
        StringBuilder serversInfo = new StringBuilder("Servers online at\n");
        for (int i = 1; i < args.length; i++) {
            try {
                Server server = new Server(http, storage, zoo, args[i]);
                final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = server.createRoute().flow(
                        actorSystem,
                        materializer
                );
                bindings.add(http.bindAndHandle(
                        routeFlow,
                        ConnectHttp.toHost(Constants.HOST, Integer.parseInt(args[i])),
                        materializer
                ));
                serversInfo.append("http://localhost:").append(args[i]).append("")
            }
        }

    }
}
