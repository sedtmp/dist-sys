package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import messages.GetServer;
import messages.StoreServers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoreActor extends AbstractActor {
    private List<String> servers = new ArrayList<>();
    private Random random = new Random();

    private String getRandomServer() {
        return servers.get(random.nextInt(servers.size()));
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreServers.class, msg -> servers = msg.getServers())
                .match(GetServer.class, msg -> getSender().tell(this.getRandomServer(), ActorRef.noSender()))
                .build();
    }
}
