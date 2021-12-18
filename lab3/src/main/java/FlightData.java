import java.io.Serializable;

public class FlightData implements Serializable {
    private int destAirportId;
    private int originAirportId;
    private float arrDelay;
    private float cancelled;

    public FlightData() {}

    public FlightData(int destAirportId, int originAirportId, float arrDelay, float cancelled) {
        this.destAirportId = destAirportId;
        this.originAirportId = originAirportId;
        this.arrDelay = arrDelay;
        this.cancelled = cancelled;
    }

    public float getArrDelay() {
        return arrDelay;
    }

    public float getCancelled() {
        return cancelled;
    }

    public int getDestAirportId() {
        return destAirportId;
    }

    public int getOriginAirportId() {
        return  originAirportId;
    }
}
