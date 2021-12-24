package actors;

import akka.actor.AbstractActor;

import java.util.List;
import java.util.Random;

public class StoreActor extends AbstractActor {
    private List<String> servers = new ;
    private Random random = new Random();

    @Override
    public Receive createReceive() {
        return receiveBuilder().build();
    }
}
