import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.ArrayList;

public class ExecuteActor extends AbstractActor {
    ActorRef storeActor;

    public ExecuteActor(ActorRef storeActor) {
        this.storeActor = storeActor;
    }

    private Test execute(TestMessage msg) {
        
    }
}
