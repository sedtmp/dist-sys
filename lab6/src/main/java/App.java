import akka.actor.ActorSystem;
import org.apache.log4j.BasicConfigurator;

public class App {
    public static void main(String[] args) {
        BasicConfigurator.configure();
        ActorSystem actorSystem = ActorSystem.create("routes");
        
    }
}
