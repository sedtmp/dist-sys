import org.apache.hadoop.io.WritableComparator;

public class AirportGroupingComparator extends WritableComparator {
    public AirportGroupingComparator() {
        super(AirportWritableComparable.class, true);
    }

    private int compare()

    @Override
    public int compare(WritableComparator first, WritableComparator second) {
        AirportWritableComparable firstAwc = (AirportWritableComparable) first;
        AirportWritableComparable secondAwc = (AirportWritableComparable) second;

    }
}
