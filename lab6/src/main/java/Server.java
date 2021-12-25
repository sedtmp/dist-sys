import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import constants.Constants;
import messages.GetServer;
import org.apache.zookeeper.*;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class Server implements Watcher {
    private final Http http;
    private final ActorRef actorStore;
    private final ZooKeeper zoo;
    private final String path;

    public Server(
            Http http,
            ActorRef actorStore,
            ZooKeeper zoo,
            String port
    ) throws InterruptedException, KeeperException {
        this.http = http;
        this.actorStore = actorStore;
        this.zoo = zoo;
        this.path = Constants.createServerPath(port);
        zoo.create(
                Constants.createNodePath(port),
                path.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL
        );
    }

    private static Route check(ActorRef actorStore, Http http, Request request) {
        if (request.hasZeroCount()) {
            return completeWithFuture(http.singleRequest(HttpRequest.create(request.getUrl())));
        } else {
            request.decreaseCount();
            return completeWithFuture(Patterns
                    .ask(actorStore, new GetServer(), Duration.ofMillis(5000))
                    .thenCompose(req -> {
                        String singleRequestUrl = String.format(
                                Constants.URL_PATTERN,
                                req,
                                request.getUrl(),
                                request.getCount()
                        );
                        return http.singleRequest(HttpRequest.create(singleRequestUrl));
                    })
            );
        }
    }

    public Route createRoute() {
        return route(path(Constants.EMPTY_STRING, () ->
                parameter(Constants.URL_PARAMETER, url ->
                        parameter(Constants.COUNT_PARAMETER, count -> {
                            App.print(String.format("Count = %s on %s", count, path));
                            return check(actorStore, http, new Request(url, count));
                        })
                )
        ));
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            zoo.getData(path, this, null);
        } catch (KeeperException | InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
