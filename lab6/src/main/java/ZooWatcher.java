import akka.actor.ActorRef;
import constants.Constants;
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

    private void sendServers() throws InterruptedException, KeeperException {
        List<String> servers = new ArrayList<>();
        for (String server : zoo.getChildren(Constants.SERVERS_PATH, this)) {
            servers.add(new String(zoo.getData(String.format(Constants.SERVER_PATH_PATTERN, ))))
        }
    }
}
