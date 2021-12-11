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

    public 
}
