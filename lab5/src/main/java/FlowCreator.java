import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.stream.Materializer;
import akka.stream.javadsl.Sink;
import javafx.util.Pair;

import java.util.concurrent.CompletionStage;

public class FlowCreator {
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

    private Sink<Pair<String, Integer>, CompletionStage<Long>> createSink() {
        
    }
}
