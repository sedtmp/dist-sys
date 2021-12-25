import akka.actor.ActorRef;
import constants.Constants;
import messages.StoreServers;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;

public class ZooWatcher implements Watcher {
    private ZooKeeper zoo;
    private ActorRef storage;

    public ZooWatcher(ZooKeeper zoo, ActorRef storage) throws InterruptedException, KeeperException {
        this.zoo = zoo;
        this.storage = storage;
        sendServers();
    }

    private static String getServerData(ZooKeeper zoo, String server) throws InterruptedException, KeeperException {
        return new String(zoo.getData(String.format(Constants.SERVER_PATH_PATTERN, server), false, null));
    }

    private void sendServers() throws InterruptedException, KeeperException {
        List<String> servers = new ArrayList<>();
        for (String server : zoo.getChildren(Constants.SERVERS_PATH, this)) {
            servers.add(getServerData(zoo, server));
        }
        storage.tell(new StoreServers(servers), ActorRef.noSender());
    }

    @Override
    public void process(WatchedEvent event) {
        try {
            zoo.getChildren(Constants.SERVERS_PATH, this);
            sendServers();
        } catch (InterruptedException | KeeperException exception) {
            exception.printStackTrace();
        }
    }
}
