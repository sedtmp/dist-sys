import java.io.Serializable;

public class FlightsSerializable implements Serializable {
    private float delayMaxTime;
    private float flightsDelay;
    private float cancelledFlights;
    private int flightsNumber;

    public FlightsSerializable() {}

    public FlightsSerializable(float delayMaxTime, float flightsDelay, float cancelledFlights, int flightsNumber) {
        this.delayMaxTime = delayMaxTime;
        this.flightsDelay = flightsDelay;
        this.cancelledFlights = cancelledFlights;
        this.flightsNumber = flightsNumber;
    }

    public float getDelayMaxTime() {
        return delayMaxTime;
    }

    public float getFlightsDelay() {
        return flightsDelay;
    }

    public float getCancelledFlights() {
        return cancelledFlights;
    }

    public int getFlightsNumber() {
        return flightsNumber;
    }

    public void setDelayMaxTime(float delayMaxTime) {
        this.delayMaxTime = delayMaxTime;
    }

    public void setFlightsDelay(float flightsDelay) {
        this.flightsDelay = flightsDelay;
    }

    public void setCancelledFlights(float cancelledFlights) {
        this.cancelledFlights = cancelledFlights;
    }

    public void setFlightsNumber(int flightsNumber) {
        this.flightsNumber = flightsNumber;
    }
    
}
