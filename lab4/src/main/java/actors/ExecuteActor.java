package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;
import constants.Constants;
import messages.StoreMessage;
import messages.TestMessage;
import tests.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class ExecuteActor extends AbstractActor {
    private ActorSelection storeActor = getContext().actorSelection(Constants.STORE_ACTOR_PATH);

    private ArrayList<Test> execute (
            String jsScript,
            String functionName,
            String testName,
            String expectedResult,
            ArrayList<Integer> params
    ) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(Constants.ENGINE);
        engine.eval(jsScript);
        Invocable invocable = (Invocable) engine;
        String result = invocable.invokeFunction(functionName, params.toArray()).toString();
        Test test = new Test(testName, expectedResult, params, expectedResult.equals(result));
        ArrayList<Test> tests = new ArrayList<Test>();
        tests.add(test);
        return tests;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                TestMessage.class, m ->  {
                    storeActor.tell(new StoreMessage(
                            m.getPackageId(),
                            execute(
                                    m.getJsScript(),
                                    m.getFunctionName(),
                                    m.getTest().getTestName(),
                                    m.getTest().getExpectedResult(),
                                    m.getTest().getParams()
                            )),
                            self()
                    );
                }).build();
    }
}
