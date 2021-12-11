import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class App {
    private final static String AIRPORTS_PATH = "resources/L_AIRPORT_ID.csv";
    private final static String FLIGHTS_PATH = "resources/664600583_T_ONTIME_sample.csv";

    public final static int ORIGIN_AIRPORT_ID = 11;
    public final static int DEST_AIRPORT_ID = 14;

    private static

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> airports = sc.textFile(AIRPORTS_PATH);
        JavaRDD<String> flights = sc.textFile(FLIGHTS_PATH);
        JavaPairRDD<Tuple2<Integer, Integer>, AirportSerializable> pairFlightsRDD = flights.mapToPair(
          s -> new Tuple2<>(
                  new Tuple2<>(
                          Integer.parseInt(s.split(",")[ORIGIN_AIRPORT_ID]),
                          Integer.parseInt(s.split(",")[DEST_AIRPORT_ID])
                  );
    }
}
