import actors.ExecuteActor;
import actors.StoreActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import constants.Constants;

public class App {
    private static final 
    private final int POOL_NUMBER = 5;

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lab4");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class), Constants.STORE_ACTOR);
        ActorRef executeActor = system.actorOf(new RoundRobinPool(), )
    }
}
