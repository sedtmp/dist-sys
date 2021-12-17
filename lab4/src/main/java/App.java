import actors.StoreActor;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import constants.Constants;

public class App {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lab4");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        storeActor = system.actorOf(Props.create(StoreActor.class), Constants.STORE_ACTOR_PATH);
    }
}