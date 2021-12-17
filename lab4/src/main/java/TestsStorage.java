import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestsStorage extends AbstractActor {
    final Map<Integer, ArrayList<Test>> tests = new HashMap<>();

    @Override
    public Receive createRecieve() {
        return Receive.
    }
}
