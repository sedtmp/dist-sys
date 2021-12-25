import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;
import constants.Constants;
import org.apache.zookeeper.*;

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

    public Route createRoute() {
        return route(path(Constants.EMPTY_STRING, () -> {
            route(get(() -> parameter("url")))
        }))
    }
}
