import actors.StoreActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.stream.ActorMaterializer;
import constants.Constants;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
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

        List<CompletionStage<ServerBinding>>

    }
}
