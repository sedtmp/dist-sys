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

    public int getOriginAirportId() {
        return originAirportId;
    }

    public int getDestAirportId() {
        return destAirportId;
    }

    public float getDelay() {
        return delay;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setOriginAirportId(int originAirportId) {
        this.originAirportId = originAirportId;
    }

    public void setDestAirportId(int destAirportId) {
        this.destAirportId = destAirportId;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
        
    }
}
