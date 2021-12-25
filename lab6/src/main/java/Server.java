import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Server implements Watcher {
    public class Server(Http, ActorRef actorConfig, ZooKeeper zoo, String port)
}
