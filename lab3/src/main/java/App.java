import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class App {
    private final static String AIRPORTS_PATH = "L_AIRPORT_ID.csv";
    private final static String FLIGHTS_PATH = "664600583_T_ONTIME_sample.csv";
    private final static String CODE = "code";
    private final static String QUOTES = "\"";
    private final static String EMPTY_STRING = "";
    private final static String NAMES_DELIMITER = "\",";
    private final static String DELAYS_DELIMITER = ",";
    private final static int NAMES_DEST_AIRPORT_ID = 0;
    private final static int NAME_AIRPORT = 1;

    public final static int ORIGIN_AIRPORT_ID = 11;
    public final static int DEST_AIRPORT_ID = 14;
    public final static int FLIGHT_DELAY = 17;
    public final static int FLIGHT_CANCELLED = 19;

    private static FlightData parseFlightData(String inputData) {
        String[] data = inputData.split(",");
        float delay = 0f;
        if (!data[FLIGHT_DELAY].equals("")) {
            delay = Float.parseFloat(data[FLIGHT_DELAY]);
        }
        float cancelled = 0f;
        if (!data[FLIGHT_CANCELLED].equals("")) {
            delay = Float.parseFloat(data[FLIGHT_CANCELLED]);
        }
        return new FlightData(delay, cancelled);
    }

    private static String removeQuotes(String value) {
        return value.replaceAll(QUOTES, EMPTY_STRING);
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flights = sc.textFile(FLIGHTS_PATH);
        JavaRDD<String> airports = sc.textFile(AIRPORTS_PATH);
        JavaPairRDD<Integer, String> airportsData = airports
                .filter(str -> !str.contains(CODE))
                .mapToPair(value -> {
                    String[] table = value.split(NAMES_DELIMITER);
                    Integer destAirportId = Integer.valueOf(removeQuotes(table[NAMES_DEST_AIRPORT_ID]));
                    return new Tuple2<>(destAirportId, table[NAME_AIRPORT]);
                });
        JavaPairRDD<Tuple2>


        JavaPairRDD<Tuple2<Integer, Integer>, FlightData> pairFlightsRDD = flights
                .filter(str -> !str.contains(CODE))
                .mapToPair(
                        s -> new Tuple2<>(
                                new Tuple2<>(
                                        Integer.parseInt(s.split(",")[ORIGIN_AIRPORT_ID]),
                                        Integer.parseInt(s.split(",")[DEST_AIRPORT_ID])),
                                parseFlightData(s)
                        )
                );
        JavaPairRDD<Tuple2<Integer, Integer>, FlightsSerializable> reducesFlightsRDD = pairFlightsRDD.aggregateByKey(
                new FlightsSerializable(),
                (a, b) -> {
                    a.merge(b);
                    return a;
                },
                (a, b) -> {
                    a.mergeAll(b);
                    return a;
                }
        );
        JavaPairRDD<Integer, String> pairAirportsRDD = airports.mapToPair(
                s -> new Tuple2<>(
                        Integer.parseInt(s.split(",", 2)[0].replaceAll("\"", "")),
                        s.split(",", 2)[1].replaceAll("\"", "")
                )
        );
        Map<Integer, String> airportsMap = pairAirportsRDD.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportsMap);
        JavaRDD<String> result = reducesFlightsRDD.map(
                T -> {
                    Map<Integer, String> airportsData = airportsBroadcasted.value();
                    String outputStr = airportsData.get(T._1._1) + " -> " + airportsData.get(T._1._2) + " :\n";
                    outputStr += "maxDelay: " + T._2.getDelayMaxTime() + "\n";
                    outputStr += "percentCancelled: " + T._2.calcCancelledPercent() + "\n";
                    return outputStr;
                }
        );
        result.saveAsTextFile("/user/sed/lab3_result");
    }
}
