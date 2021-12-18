import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class App {
    private static String removeQuotes(String value) {
        return value.replaceAll(Constants.QUOTES, Constants.EMPTY_STRING);
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flights = sc.textFile(Constants.FLIGHTS_PATH);
        JavaRDD<String> airports = sc.textFile(Constants.AIRPORTS_PATH);
        JavaPairRDD<Integer, String> airportNamesData = airports
                .filter(str -> !str.contains(Constants.CODE))
                .mapToPair(value -> {
                    String[] table = value.split(Constants.NAMES_DELIMITER);
                    Integer destAirportId = Integer.valueOf(
                            removeQuotes(table[Constants.NAMES_DEST_AIRPORT_ID])
                    );
                    return new Tuple2<>(destAirportId, table[Constants.NAME_AIRPORT]);
                });
        JavaPairRDD<Tuple2<Integer, Integer>, FlightData> flightDelaysData = flights
                .filter(str -> !str.contains(Constants.YEAR_COLUMN))
                .mapToPair(value -> {
                    String[] table = value.split(Constants.DELAYS_DELIMITER);
                    int destAirportId = Integer.parseInt(
                            table[Constants.NAMES_DEST_AIRPORT_ID]
                    );
                    int originalAirportId = Integer.parseInt(
                            table[Constants.ORIGIN_AIRPORT_ID]
                    );
                    float arrDelay 
                });


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
