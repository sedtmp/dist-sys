package constants;

public class Constants {
    public static final String SERVERS_PATH = "/servers";
    public static final String SERVER_PATH_PATTERN = SERVERS_PATH + "/" + "%s";
    public static final String HOST = "localhost";

    public static String createPath(String port) {
        return String.format("%s:%s", HOST, port);
    }
}