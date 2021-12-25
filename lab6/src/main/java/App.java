import actors.StoreActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import constants.Constants;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.ZooKeeper;

public class App {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        ActorSystem actorSystem = ActorSystem.create("routes");
        ActorRef storage = actorSystem.actorOf(Props.create(StoreActor.class));
        final ActorMaterializer materializer = ActorMaterializer.create(actorSystem);
        final Http http = Http.get(actorSystem);

        ZooKeeper zoo = null;
        try {
            zoo = new ZooKeeper(args[Constants.ZOOKEEPER_ADDRESS_INDEX])
        }

    }
}
