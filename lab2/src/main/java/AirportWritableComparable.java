import org.apache.hadoop.io.WritableComparable;

public class AirportWritableComparable implements WritableComparable<AirportWritableComparable> {
    private int airportId;
    private int code;

    AirportWritableComparable() {}

    AirportWritableComparable(int airportId, int code) {
        this.airportId = airportId;
        this.code = code;
    }

    public int getAirportId() {
        return airportId;
    }

    public int getCode() {
        return code;
    }

    @Override
    
}
