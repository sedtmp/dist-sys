package actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import messages.GetMessage;
import messages.StoreMessage;

import java.util.HashMap;

public class CacheActor extends AbstractActor {
    private final HashMap<String, Integer> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {
                    storage.putIfAbsent(m.getUrl(), m.getTime());
                })
                .match(GetMessage.class, req -> {
                    getSender().tell(storage.getOrDefault(req.getUrl(), -1));
                    ;
                })
                .build();
    }
}
