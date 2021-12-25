import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Server implements Watcher {
    private final Http http;
    private final ActorRef actorConfig;
    private final ZooKeeper zoo;
    

    public Server(Http http, ActorRef actorConfig, ZooKeeper zoo, String port) {
        this.http = http;
    }
}
