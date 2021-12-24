import java.io.Serializable;

public class Flights implements Serializable {
    private static final String OUTPUT_FORMAT = "MaxDelay: %d; Percent of delays: %d; Percent of cancelled: %d";

    private float maxArrDelay;
    private int cancelledCount;
    private int flightsCount;
    private int delaysCount;

    public Flights() {}

    public Flights (
            float maxArrDelay,
            int cancelledCount,
            int flightsCount,
            int delaysCount) {
        this.maxArrDelay = maxArrDelay;
        this.cancelledCount = cancelledCount;
        this.flightsCount = flightsCount;
        this.delaysCount = delaysCount;
    }

    public float getMaxArrDelay() {
        return maxArrDelay;
    }

    public int getCancelledCount() {
        return cancelledCount;
    }

    public int getFlightsCount() {
        return flightsCount;
    }

    public int getDelaysCount() {
        return delaysCount;
    }

    public static Flights mergeValue(Flights flights, float maxArrDelay, boolean isDelayed, boolean isCancelled) {
        return new Flights(
                Math.max((float) flights.getMaxArrDelay(), (float) maxArrDelay),
                isCancelled ? flights.getCancelledCount() + 1 : flights.getCancelledCount(),
                flights.getFlightsCount() + 1,
                isDelayed ? flights.getDelaysCount() + 1 : flights.getDelaysCount()
        );
    }

    public static Flights merge(Flights flights, Flights otherFlights) {
        return new Flights(
                Math.max(flights.getMaxArrDelay(), otherFlights.getMaxArrDelay()),
                flights.getCancelledCount() + otherFlights.getCancelledCount(),
                flights.getFlightsCount() + otherFlights.getCancelledCount(),
                flights.getDelaysCount() + otherFlights.getDelaysCount()
        );
    }

    public float calcCancelledPercent() {
        return cancelledCount / (float) flightsCount * 100;
    }

    public float calcDelaysPercent() {
        return delaysCount / (float) flightsCount * 100;
    }

    public String toOutputString() {
        float cancelledPercent = calcCancelledPercent();
        float delaysPercent = calcDelaysPercent();
        return String.format(OUTPUT_FORMAT, maxArrDelay, delaysPercent, cancelledPercent);
    }
}
