import actors.CacheActor;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import constants.Constants;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class App {
    public static void main(String[] args) throws IOException {
        ActorSystem system = ActorSystem.create("lab5");
        ActorRef cacheActor = system.actorOf(Props.create(CacheActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        FlowCreator creator = new FlowCreator(http, system, cacheActor, materializer);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = creator.createFlow();
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(Constants.HOST, Constants.PORT),
                materializer
        );
        System.out.println(String.format("Server is starting at %s", Constants.SERVER));
        System.in.read();
        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> system.terminate());
    }
}
