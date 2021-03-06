package actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import messages.GetMessage;
import messages.StoreMessage;
import tests.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    final Map<Integer, ArrayList<Test>> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {
                    if (storage.containsKey(m.getPackageId())) {
                        ArrayList<Test> results = storage.get(m.getPackageId());
                        results.addAll(m.getTests());
                        storage.replace(m.getPackageId(), results);
                    } else {
                        storage.put(m.getPackageId(), m.getTests());
                    }

                }
                ).match(GetMessage.class, req -> {
                    sender().tell(new StoreMessage(req.getPackageId(), storage.get(req.getPackageId())), self());
                }
        ).build();
    }
}
