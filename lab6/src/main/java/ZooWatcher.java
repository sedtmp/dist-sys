import akka.actor.ActorRef;
import org.apache.zookeeper.*;

public class ZooWatcher implements Watcher {
    private ZooKeeper zoo;
    private ActorRef storage;

    public ZooWatcher(ZooKeeper zoo, ActorRef storage) {
        this.zoo = zoo;
        this.storage = storage;
        send
    }
}
