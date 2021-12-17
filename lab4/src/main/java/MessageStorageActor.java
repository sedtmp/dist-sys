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

        public StoreMessage(Integer packageId, String result) {
            this.packageId = packageId;
            this.result = result;
        }

        public Integer getPackageId() {
            return packageId;
        }

        public String getResult() {
            return result;
        }
    }

    public static class GetMessage {
        private
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
