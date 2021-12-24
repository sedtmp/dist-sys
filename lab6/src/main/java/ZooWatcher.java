import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class ZooWatcher implements Watcher {
    private ZooKeeper zoo;
    private ActorRef storage;

    public ZooWatcher(ZooKeeper zoo, ActorRef storage) {
        this.zoo = zoo;
        this.storage = storage;
    }

    private void sendServers() {
        List<String> servers = new ArrayList<>();
        
    }
}
