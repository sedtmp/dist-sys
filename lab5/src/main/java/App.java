import actors.CacheActor;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

public class App {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lab5");
        ActorRef cacheActor = system.actorOf(Props.create(CacheActor.class));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        FlowCreator creator = new FlowCreator(http, system, cacheActor, materializer);
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = 
    }
}
