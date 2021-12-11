import java.io.Serializable;

public class FlightData implements Serializable {
    private float delay;
    private float cancelled;

    public FlightData() {}

    public FlightData(float delay, float cancelled) {
        this.delay = delay;
        this.cancelled = cancelled;
    }

    public float getDelay() {
        return delay;
    }

    public float getCancelled() {
        return cancelled;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public void setCancelled(float cancelled) {
        this.cancelled = cancelled;
    }
}
