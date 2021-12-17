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
                        results.add(msg.getResult());
                        storage.replace(t.getPackageId(), tests);
                        return;
                    }
                    storage.put(t.getPackageId(), t.getTests());
                }
        ).match()
    }
}
