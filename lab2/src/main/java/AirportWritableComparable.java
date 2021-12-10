import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
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

    public int compareByAirportId(AirportWritableComparable awc) {
        if (airportId > )
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(airportId);
        dataOutput.writeInt(code);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        airportId = dataInput.readInt();
        code = dataInput.readInt();
    }

    @Override
    public int compareTo(AirportWritableComparable awc) {
        if (airportId > awc.airportId) {
            return 1;
        }
        if (airportId < awc.airportId) {
            return -1;
        }
        return Integer.compare(code, awc.code);
    }
}
