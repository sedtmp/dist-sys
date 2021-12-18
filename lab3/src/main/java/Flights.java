import java.io.Serializable;

public class Flights implements Serializable {
    private static final String OUTPUT_FORMAT = "MaxDelay: %d; Percent of delays: %d; Percent of cancelled: %d";

    private float maxArrDelay;
    private float cancelledCount;
    private int flightsCount;
    private int delaysCount;

    public Flights() {}

    public Flights (
            float maxArrDelay,
            float cancelledFlights,
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

    public float getCancelledCount() {
        return cancelledCount;
    }

    public int getFlightsCount() {
        return flightsCount;
    }

    public int getDelaysCount() {
        return delaysCount;
    }

    public static Flights merge(Flights flights, float maxArrDelay, boolean isDelayed, boolean isCancelled) {
        return new Flights(
                flights.getFlightsCount() + 1,
                isDelayed ? flights.getDelaysCount() + 1 : flights.getDelaysCount(),
                Math.max(flights.getMaxArrDelay(), )
        );
    }

    public static Flights mergeAll(Flights data) {
        if (data.getDelayMaxTime() > delayMaxTime) {
            delayMaxTime = data.getDelayMaxTime();
        }
        flightsCount += data.getFlightsCount();
        cancelledCount += data.getCancelledCount();
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
        return String.format(OUTPUT_FORMAT, delayMaxTime, delaysPercent, cancelledPercent);
    }
}
