import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageStorageActor extends AbstractActor {
    final Map<Integer, ArrayList<Test>> storage = new HashMap<>();

    public static class StoreMessage {
        private Integer packageId;
        private String result;

        public StoreMessage()
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                Tests.class, t -> {
                    if (storage.containsKey(t.getPackageId())) {
                        ArrayList<Test> tests = storage.get(t.getPackageId());
                        tests.addAll(t.getTests());
                        storage.replace(t.getPackageId(), tests);
                        return;
                    }
                    storage.put(t.getPackageId(), t.getTests());
                }
        ).match()
    }
}
