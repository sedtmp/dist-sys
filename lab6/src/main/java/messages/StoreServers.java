package messages;

import java.util.List;

public class StoreServers {
    private List<String> servers;

    public StoreServers(List<String> servers) {
        this.servers = servers;
    }

    public List<String> getServers() {
        return servers;
    }
}
