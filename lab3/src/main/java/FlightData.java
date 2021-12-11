import java.io.Serializable;

public class AirportSerializable implements Serializable {
    private float delay;
    private boolean cancelled;

    public AirportSerializable() {}

    public AirportSerializable(float delay, boolean cancelled) {
        this.delay = delay;
        this.cancelled = cancelled;
    }

    public float getDelay() {
        return delay;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
