import org.apache.hadoop.io.WritableComparable;

import javax.xml.crypto.Data;
import java.io.DataOutput;
import java.io.IOException;

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
    public void write(DataOutput dataOutput) throws IOException {
        
    }
}
