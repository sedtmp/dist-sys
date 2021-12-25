package constants;

public class Constants {
    public static final String SERVERS_PATH = "/servers";
    public static final String SERVER_PATH_PATTERN = SERVERS_PATH + "/" + "%s";
    public static final String HOST = "localhost";
    public static final String EMPTY_STRING = "";
    public static final int ZOOKEEPER_ADDRESS_INDEX = 0;
    public static final int PORT_INDEX = 1;
    public static final int ZOOKEEPER_TIMEOUT = 2500;
    public static final String ERROR = "ZERO SERVERS IS RUNNING";
    public static final String URL_PATTERN = "http://%s/?url=%s&count=%d";
    public static final String URL_PARAMETER = "url";
    public static final String COUNT_PARAMETER = "count";

    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";


    public static String createServerPath(String port) {
        return String.format("%s:%s", HOST, port);
    }

    public static String createServerUrl(String port) {
        return String.format("http://localhost:%s/\n", port);
    }

    public static String createNodePath(String port) {
        return String.format("%s/%s:%s", SERVERS_PATH, HOST, port);
    }

    public static boolean isZero(int value) {
        return value == 0;
    }
}