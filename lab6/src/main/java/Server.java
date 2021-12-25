import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import constants.Constants;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Server implements Watcher {
    private final Http http;
    private final ActorRef actorConfig;
    private final ZooKeeper zoo;
    private final String path;

    public Server(Http http, ActorRef actorConfig, ZooKeeper zoo, String port) {
        this.http = http;
        this.actorConfig = actorConfig;
        this.zoo = zoo;
        this.path = Constants.createServerPath(port);
        zoo.create()
    }


}
