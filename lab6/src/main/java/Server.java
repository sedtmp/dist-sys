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
    private final ActorRef actorConfig;
    private final ZooKeeper zoo;
    private final String path;

    public Server(
            Http http,
            ActorRef actorConfig,
            ZooKeeper zoo,
            String port
    ) throws InterruptedException, KeeperException {
        this.http = http;
        this.actorConfig = actorConfig;
        this.zoo = zoo;
        this.path = Constants.createServerPath(port);
        zoo.create(
                Constants.createNodePath(path),
                path.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL
        );
    }

    private static Route check(ActorRef actorConfig, Http http, Request request) {
        if (request.hasZeroCount()) {
            return completeWithFuture(http.singleRequest(HttpRequest.create(request.getUrl())));
        } else {
            request.decrementCount();
            return completeWithFuture(Patterns
                    .ask(actorConfig, new GetServer(), Duration.ofMillis(5000))
                    .thenCompose(req -> {
                        String singleRequestUrl = String.format(
                                "http://%s/?url=%s&count=%d",
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
        return route(path(() ->
                parameter("url", url ->
                        parameter("count", count ->
                                check(actorConfig, http, new Request(url, count))
                    )
            )
        ));
    }
}
