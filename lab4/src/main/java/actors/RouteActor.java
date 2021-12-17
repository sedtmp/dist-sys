package actors;

import akka.actor.AbstractActor;
import akka.actor.ActorSelection;
import akka.japi.pf.ReceiveBuilder;
import constants.Constants;
import messages.PackageMessage;
import messages.TestMessage;
import tests.Test;

public class RouteActor extends AbstractActor {
    private ActorSelection router = getContext().actorSelection(Constants.ROUTE_ACTOR_PATH);

    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                PackageMessage.class, m -> {
                    for (Test test : m.getTests()) {
                        router.tell(
                                new TestMessage(
                                        m.getPackageId(),
                                        m.getJsScript(),
                                        m.getFunctionName(),
                                        test),
                                self()
                        );
                    }
                }
        )
    }
}
