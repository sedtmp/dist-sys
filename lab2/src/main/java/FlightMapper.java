import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    private static final int DEST_AIRPORT_ID = 14;
    private static final int ARR_DELAY_NEW = 18;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split(",");
        String airportIdStr = line[DEST_AIRPORT_ID];
        if (!airportIdStr.equals("\"DEST_AIRPORT_ID\"")) {
            int airportId = Integer.parseInt(airportIdStr);
            String delay = line[ARR_DELAY_NEW];
            if (!delay.isEmpty()) {
                context.write(new AirportWritableComparable(airportId, 1), new Text(delay));
            }
        }
    }
}
