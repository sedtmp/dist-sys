package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import messages.GetMessage;
import messages.StoreMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoreActor extends AbstractActor {
    private List<String> servers = new ArrayList<>();
    private Random random = new Random();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreMessage.class, msg -> this.servers = msg.getServers())
                .match(GetMessage.class, msg -> getSender().tell(this.getRandomServer(), ActorRef.noSender()))
                .build();
    }

    private String getRandomServer() {
        return servers.get(random.nextInt(servers.size()));
    }
}
