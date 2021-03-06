import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {
    public AirportGroupingComparator() {
        super(AirportWritableComparable.class, true);
    }

    @Override
    public int compare(WritableComparable first, WritableComparable second) {
        AirportWritableComparable firstAwc = (AirportWritableComparable) first;
        AirportWritableComparable secondAwc = (AirportWritableComparable) second;
        return Integer.compare(firstAwc.getAirportId(), secondAwc.getAirportId());
    }
}
