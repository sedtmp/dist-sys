package constants;

public class Constants {
    public static final String SERVERS_PATH = "/servers";
    public static final String SERVER_PATH_PATTERN = SERVERS_PATH + "/" + "%s";
    public static final String HOST = "localhost";
    public static final String EMPTY_STRING = "";

    public static String createServerPath(String port) {
        return String.format("%s:%s", HOST, port);
    }

    public static String createNodePath(String serverPath) {
        return String.format("/%s%s", SERVERS_PATH, serverPath);
    }

    public static boolean isZeroCount(String count) {
        return count.equals("0");
    }
}