import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestsStorage extends AbstractActor {
    final Map<Integer, ArrayList<Test>> tests = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                Tests.class, t -> {
                    if (tests.containsKey(t.getPackageId())) {
                        ArrayList<Test>
                    }
                }
        )
    }
}
