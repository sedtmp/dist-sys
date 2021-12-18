import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;

public class FlowCreator {
    private static final 

    private final Http http;
    private final ActorSystem system;
    private final ActorRef cacheActor;
    private final Materializer materializer;

    public FlowCreator(Http http,
                       ActorSystem system,
                       ActorRef cacheActor,
                       Materializer materializer) {
        this.http = http;
        this.system = system;
        this.cacheActor = cacheActor;
        this.materializer = materializer;
    }

    public Flow<HttpRequest, HttpResponse, NotUsed> createFlow() {
        return Flow.of(HttpRequest.class)
                .map(req -> {})
                .mapAsync()
    }
}
