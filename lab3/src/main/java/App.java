import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class App {
    private static final String OUTPUT_PATTERN = "%s -> %s\n%s";

    private static String removeQuotes(String value) {
        return value.replaceAll(Constants.QUOTES, Constants.EMPTY_STRING);
    }

    private static float checkNull(String value) {
        if (value.equals(Constants.EMPTY_STRING)) {
            return Constants.ZERO;
        }
        return Float.parseFloat(value);
    }

    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> flightsDist = sc.textFile(Constants.FLIGHTS_PATH);
        JavaRDD<String> airportsDist = sc.textFile(Constants.AIRPORTS_PATH);
        JavaPairRDD<Integer, String> airportNamesData = airportsDist
                .filter(str -> !str.contains(Constants.CODE))
                .mapToPair(value -> {
                    String[] table = value.split(Constants.NAMES_DELIMITER);
                    Integer destAirportId = Integer.valueOf(
                            removeQuotes(table[Constants.NAMES_DEST_AIRPORT_ID])
                    );
                    return new Tuple2<>(destAirportId, table[Constants.NAME_AIRPORT]);
                });
        JavaPairRDD<Tuple2<Integer, Integer>, FlightData> flightDelaysData = flightsDist
                .filter(str -> !str.contains(Constants.YEAR))
                .mapToPair(value -> {
                    String[] table = value.split(Constants.DELAYS_DELIMITER);
                    int destAirportId = Integer.parseInt(
                            table[Constants.NAMES_DEST_AIRPORT_ID]
                    );
                    int originalAirportId = Integer.parseInt(
                            table[Constants.ORIGIN_AIRPORT_ID]
                    );
                    float arrDelay = checkNull(table[Constants.ARR_DELAY]);
                    float isCancelled = Float.parseFloat(table[Constants.CANCELLED]);
                    return new Tuple2<>(
                            new Tuple2<>(originalAirportId, destAirportId),
                            new FlightData(destAirportId, originalAirportId, arrDelay, isCancelled)
                    );
                });
        JavaPairRDD<Tuple2<Integer, Integer>, Flights> flights = flightDelaysData
                .combineByKey(p ->
                                new Flights(
                                        p.getArrDelay(),
                                        p.getCancelled() == Constants.ZERO ? 0 : 1,
                                        1,
                                        p.getArrDelay() > Constants.ZERO ? 1 : 0
                                ), (flight, p) -> Flights.mergeValue(
                                        flight,
                                        p.getArrDelay(),
                                        p.getArrDelay() != Constants.ZERO,
                                        p.getCancelled() != Constants.ZERO
                                ),
                        Flights::merge);
        JavaPairRDD<Tuple2<Integer, Integer>, String> flightStrings = flights
                .mapToPair(value -> {
                    value._2();
                    return new Tuple2<>(value._1(), value._2().toOutputString());
                });

        final Broadcast<Map<Integer, String>> broadcast = sc.broadcast(airportNamesData.collectAsMap());
        JavaRDD<String> result = flightStrings.map(value -> {
                    Map<Integer, String> airportNames = broadcast.value();
                    String startAiportName = airportNames.get(value._1()._1());
                    String finishAirportName = airportNames.get(value._1()._2());
                    return String.format(OUTPUT_PATTERN, startAiportName, finishAirportName, value._2());
                }
        );
        result.saveAsTextFile("hdfs://localhost:9000/user/sed/output");
    }
}
