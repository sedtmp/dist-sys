import java.io.Serializable;

public class FlightsSerializable implements Serializable {
    private static final String OUTPUT_FORMAT = "MaxDelay: %d; Percent of delays: %d; Percent of cancelled: %d";

    private float delayMaxTime;
    private float cancelledCount;
    private int flightsCount;
    private int delaysCount;

    public FlightsSerializable() {}

    public FlightsSerializable(
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

    public void merge(FlightData flightData) {
        if (flightData.getDelay() > delayMaxTime) {
            delayMaxTime = flightData.getDelay();
        }
        flightsCount++;
        if (flightData.getDelay() < 0f || flightData.getCancelled() == 1f) {
            flightsCount++;
        }
    }

    public void mergeAll(FlightsSerializable data) {
        if (data.getDelayMaxTime() > delayMaxTime) {
            delayMaxTime = data.getDelayMaxTime();
        }
        flightsCount += data.getFlightsCount();
        cancelledCount += data.getCancelledFlights();
    }

    public float calcCancelledPercent() {
        return cancelledFlights / (float) flightsCount * 100;
    }

    public float calcDelaysPercent() {
        retun
    }

    public String toOutString() {
        float cancelledPercent = calcCancelledPercent();
        return String.format(OUTPUT_FORMAT, delayMaxTime, flightsCount, )
    }
}
