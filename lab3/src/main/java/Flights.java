import java.io.Serializable;

public class Flights implements Serializable {
    private static final String OUTPUT_FORMAT = "MaxDelay: %d; Percent of delays: %d; Percent of cancelled: %d";

    private float delayMaxTime;
    private float cancelledCount;
    private int flightsCount;
    private int delaysCount;

    public Flights() {}

    public Flights (
            float delayMaxTime,
            float cancelledFlights,
            int flightsCount,
            int delaysCount) {
        this.delayMaxTime = delayMaxTime;
        this.cancelledCount = cancelledCount;
        this.flightsCount = flightsCount;
        this.delaysCount = delaysCount;
    }

    public float getDelayMaxTime() {
        return delayMaxTime;
    }

    public float getCancelledCount() {
        return cancelledCount;
    }

    public int getFlightsCount() {
        return flightsCount;
    }

    public static Flights merge(Flights flights, float maxArrDelay, boolean isDelayed, boolean) {
        if (flightData.getArrDelay() > delayMaxTime) {
            delayMaxTime = flightData.getArrDelay();
        }
        flightsCount++;
        if (flightData.getArrDelay() < 0f || flightData.getCancelled() == 1f) {
            flightsCount++;
        }
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
