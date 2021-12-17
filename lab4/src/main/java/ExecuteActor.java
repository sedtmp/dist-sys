import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExecuteActor extends AbstractActor {
    ActorRef storeActor;

    public ExecuteActor(ActorRef storeActor) {
        this.storeActor = storeActor;
    }

    private Test execute(TestMessage message) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(Constants.ENGINE);
        engine.eval(message.getJsScript());
        Invocable invocable = (Invocable) engine;
        String result = invocable.invokeFunction(
                message.getFunctionName(),
                message.getTest().getParams().toArray()
        ).toString();
        Test test =
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                TestMessage.class, m ->  {

                }
        ).build();
    }
}
