import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class App {
    private final static String airportsPath = "resources/L_AIRPORT_ID.csv";
    private final static String fligthsPath = "resources/L_AIRPORT_ID.csv";

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
    }
}
