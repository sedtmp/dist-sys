import java.io.Serializable;

public class AirportSerializable implements Serializable {
    private int originAirportId;
    private int destAirportId;
    private float delay;
    private boolean cancelled;

    public AirportSerializable() {}

    public AirportSerializable(int originAirportId, int destAirportId, float delay, boolean cancelled) {
        this.originAirportId = originAirportId;
        this.destAirportId = destAirportId;
        this.delay = delay;
        this.cancelled = cancelled;
    }

    public int get
}
