import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;

public class ExecuteActor extends AbstractActor {
    ActorRef storeActor;

    public ExecuteActor(ActorRef storeActor) {
        this.storeActor = storeActor;
    }

    private Test execute(TestMessage msg) {
        ScriptEngine engine = new ScriptEngineManager()
    }
}
