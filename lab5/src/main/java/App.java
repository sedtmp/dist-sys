import actors.CacheActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;

public class App {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lab5");
        ActorRef cacheActor = system.actorOf(Props.create(CacheActor.class));
        final Http http = Http.get(system);
        
    }
}
