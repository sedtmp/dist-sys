package messages;

import java.util.List;

public class StoreMessage {
    private List<String> servers;

    public StoreMessage(List<String> servers) {
        this.servers = servers;
    }
}
