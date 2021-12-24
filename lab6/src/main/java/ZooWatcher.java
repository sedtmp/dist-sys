import akka.actor.ActorRef;
import org.apache.zookeeper.ZooKeeper;

public class ZooWatcher {
    private ZooKeeper zoo;
    private ActorRef storage;
}
