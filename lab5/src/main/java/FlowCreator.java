import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.pattern.Patterns;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import akka.japi.Pair;
import messages.GetMessage;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class FlowCreator {
    private static final String TEST_URL = "testUrl";
    private static final String COUNT = "count";
    private static final int MAP_ASYNC = 1;
    private static final int TIME_OUT = 5;

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
                .map(req -> {
                    Query query = req.getUri().query();
                    String url = query.get(TEST_URL).get();
                    int count = Integer.parseInt(query.get(COUNT).get());
                    return new Pair<String, Integer>(url, count);
                })
                .mapAsync(MAP_ASYNC, req -> {
                    CompletionStage<Object> stage = Patterns.ask(
                            cacheActor,
                            new GetMessage(req.first()),
                            Duration.ofSeconds(TIME_OUT)
                    );
                    return stage.thenCompose(res -> {
                        if ((Integer) res >= 0) {
                            return CompletableFuture.completedFuture(new Pair<>(req.first(), (Integer) res));
                        }
                        Flow<Pair<String, Integer>, Integer, NotUsed> flow = Flow.<Pair<String, Integer>>create()
                                .
                    });
                })
                .map(req -> {})
    }
}
