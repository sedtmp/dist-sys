import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import org.apache.log4j.BasicConfigurator;

public class App {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        ActorSystem actorSystem = ActorSystem.create("routes");
        ActorRef storage = actorSystem.actorOf(Props.create(Sto))
    }
}
