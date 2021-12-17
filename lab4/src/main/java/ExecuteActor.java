import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExecuteActor extends AbstractActor {
    ActorRef storeActor;

    public ExecuteActor(ActorRef storeActor) {
        this.storeActor = storeActor;
    }

    private Test execute(TestMessage msg) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(Constants.ENGINE);
        engine.eval(msg.getJsScript());
        Invocable invocable = (Invocable) engine;
        String result = invocable.invokeFunction(msg.getFunctionName(), );

    }

    @Override
    public Receive createReceive()
}
