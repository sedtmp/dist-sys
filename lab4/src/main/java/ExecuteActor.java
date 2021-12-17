import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class ExecuteActor extends AbstractActor {
    ActorRef storeActor;

    public ExecuteActor(ActorRef storeActor) {
        this.storeActor = storeActor;
    }

    private Test execute(String script, String functionTitle, String testName,)
}
