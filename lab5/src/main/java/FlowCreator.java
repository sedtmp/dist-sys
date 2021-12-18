import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.stream.Materializer;

public class FlowCreator {
    private final Http
    private final ActorRef cacheActor;
    private final Materializer materializer;

    public FlowCreator(ActorRef cacheActor, Materializer materializer) {
        this.cacheActor = cacheActor;
        this.materializer = materializer;
    }
}
