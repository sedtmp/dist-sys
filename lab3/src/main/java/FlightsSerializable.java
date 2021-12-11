import java.io.Serializable;

public class FlightsSerializable implements Serializable {
    private float delayMaxTime;
    private float cancelledFlights;
    private int flightsCount;

    public FlightsSerializable() {}

    public FlightsSerializable(float delayMaxTime, float cancelledFlights, int flightsCount) {
        this.delayMaxTime = delayMaxTime;
        this.cancelledFlights = cancelledFlights;
        this.flightsCount = flightsCount;
    }

    public float getDelayMaxTime() {
        return delayMaxTime;
    }

    public float getCancelledFlights() {
        return cancelledFlights;
    }

    public int getFlightsCount() {
        return flightsCount;
    }

    public void setDelayMaxTime(float delayMaxTime) {
        this.delayMaxTime = delayMaxTime;
    }

    public void setCancelledFlights(float cancelledFlights) {
        this.cancelledFlights = cancelledFlights;
    }

    public void setFlightsCount(int flightsCount) {
        this.flightsCount = flightsCount;
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
        cancelledFlights += data.getCancelledFlights();
    }

    public float calcCancelledPercent() {
        return cancelledFlights
    }
}
