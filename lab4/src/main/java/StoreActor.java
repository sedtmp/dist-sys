import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    final Map<Integer, ArrayList<Test>> storage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                StoreMessage.class, msg -> {
                    if (storage.containsKey(msg.getPackageId())) {
                        ArrayList<Test> results = storage.get(msg.getPackageId());
                        results.addAll(msg.getTests());
                        storage.replace(msg.getPackageId(), results);
                        return;
                    }
                    storage.put(msg.getPackageId(), msg.getTests());
                }
        ).match(GetMessage.class, req -> sender().tell(
                new StoreMessage(req.)
        ))
    }
}
