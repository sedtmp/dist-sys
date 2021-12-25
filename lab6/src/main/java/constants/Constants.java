package constants;

public class Constants {
    public static final String SERVERS_PATH = "/servers";
    public static final String SERVER_PATH_PATTERN = SERVERS_PATH + "/" + "%s";
    public static final String HOST = "localhost";
    public static final String EMPTY_STRING = "";
    public static final int ZOOKEEPER_ADDRESS_INDEX = 0;
    public static final int ZOOKEEPER_TIMEOUT = 3000;

    public static String createServerPath(String port) {
        return String.format("%s:%s", HOST, port);
    }

    public static String createNodePath(String port) {
        return String.format("%s/%s:%s", SERVERS_PATH, HOST, port);
    }

    public static boolean isZero(int value) {
        return value == 0;
    }
}