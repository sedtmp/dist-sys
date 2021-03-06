import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.io.Text;

public class AirportPartitioner extends Partitioner<AirportWritableComparable, Text> {
    @Override
    public int getPartition(AirportWritableComparable awc, Text text, int numReduceTasks) {
        return (awc.getAirportId() & Integer.MAX_VALUE) % numReduceTasks;
    }
}
