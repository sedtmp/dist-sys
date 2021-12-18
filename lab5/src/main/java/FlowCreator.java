import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.Materializer;

public class FlowCreator {
    private final Http http;
    private final ActorSystem system;
    private final ActorRef cacheActor;
    private final Materializer materializer;

    public FlowCreator(Http http,
                       ActorSystem actorSystem,
                       ActorRef cacheActor,
                       Materializer materializer) {
        this.http = http;
        this.system = 
        this.cacheActor = cacheActor;
        this.materializer = materializer;
    }
}
