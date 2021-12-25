import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.pattern.Patterns;
import akka.stream.Materializer;
import akka.stream.javadsl.Flow;
import akka.japi.Pair;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import constants.Constants;
import messages.GetMessage;
import messages.StoreMessage;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class FlowCreator {
    public static Flow<HttpRequest, HttpResponse, NotUsed> createFlow(
            ActorRef cacheActor,
            Materializer materializer
    ) {
        return Flow.of(HttpRequest.class)
                .map(req -> {
                    Query query = req.getUri().query();
                    String url = query.get(Constants.TEST_URL).get();
                    int count = Integer.parseInt(query.get(Constants.COUNT).get());
                    System.out.println(String.format("%s - %d", url, count));
                    return new Pair<>(url, count);
                })
                .mapAsync(Constants.MAP_ASYNC, req -> {
                    CompletionStage<Object> stage = Patterns.ask(
                            cacheActor,
                            new GetMessage(req.first()),
                            Duration.ofSeconds(Constants.TIME_OUT)
                    );
                    return stage.thenCompose(res -> {
                        if ((Integer) res >= Constants.ZERO) {
                            return CompletableFuture.completedFuture(new Pair<>(req.first(), (Integer) res));
                        } else {
                            Flow<Pair<String, Integer>, Integer, NotUsed> flow = Flow.<Pair<String, Integer>>create()
                                    .mapConcat(pair -> new ArrayList<>(Collections.nCopies(
                                            pair.second(),
                                            pair.first()
                                    )))
                                    .mapAsync(req.second(), url -> {
                                        long start = System.currentTimeMillis();
                                        Request requestTemp = Dsl.get(url).build();
                                        CompletableFuture<Response> responseCompletableFuture = Dsl
                                                .asyncHttpClient()
                                                .executeRequest(requestTemp)
                                                .toCompletableFuture();
                                        return responseCompletableFuture.thenCompose(response -> {
                                            int responseTime = (int) (System.currentTimeMillis() - start);
                                            System.out.println(responseTime);
                                            return CompletableFuture.completedFuture(responseTime);
                                        });
                                    });
                            return Source
                                    .single(req)
                                    .via(flow)
                                    .toMat(Sink.fold(Constants.ZERO, Integer::sum), Keep.right())
                                    .run(materializer)
                                    .thenApply(sum -> new Pair<>(req.first(), (sum / req.second())));
                        }
                    });
                })
                .map(req -> {
                    cacheActor.tell(new StoreMessage(req.first(), req.second()), ActorRef.noSender());
                    System.out.println(String.format("Average response time = %d", req.second()));
                    return HttpResponse.create().withEntity(req.second().toString() + Constants.NEW_LINE);
                });
    }
}
