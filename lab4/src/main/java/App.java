import actors.ExecuteActor;
import actors.RouteActor;
import actors.StoreActor;
import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import constants.Constants;
import messages.GetMessage;
import messages.PackageMessage;
import scala.concurrent.Future;

import static akka.http.javadsl.server.Directives.*;

public class App {
    private Route createRoute(ActorRef storeActor, ActorRef routeActor) {
        return route(
                get(() -> parameter(Constants.PACKAGE_ID, packageId -> {
                    Future<Object> result = Patterns.ask(
                           storeActor,
                           new GetMessage(Integer.parseInt(packageId)),
                           Constants.TIME_OUT_MILLIS
                   );
                   return completeOKWithFuture(result, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(PackageMessage.class), m -> {
                    routeActor.tell(m, ActorRef.noSender());
                    return complete("Test started");
                }))
        );
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("lab4");
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class), Constants.STORE_ACTOR);
        ActorRef executeActor = system.actorOf(
                new RoundRobinPool(Constants.POOL_NUMBER).props(Props.create(ExecuteActor.class)),
                Constants.EXECUTE_ACTOR
        );
        ActorRef routeActor = system.actorOf(Props.create(RouteActor.class), Constants.ROUTE_ACTOR);

        App app = new App();

        final Flow<HttpRequest, HttpResponse, ?> routeFlow = app.createRoute(storeActor, routeActor)
    }
}
