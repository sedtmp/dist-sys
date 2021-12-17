import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    private static final int DEST_AIRPORT_ID = 14;
    private static final int ARR_DELAY_NEW = 18;

    private static final String COMMA = ",";
    private static final String DEST_AIRPORT_ID_COLUMN = "\"DEST_AIRPORT_ID\"";

    public static final int FLIGHT_DATA_TYPE = 1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split(COMMA);
        String airportIdStr = line[DEST_AIRPORT_ID];
        if (!airportIdStr.equals(DEST_AIRPORT_ID_COLUMN)) {
            int airportId = Integer.parseInt(airportIdStr);
            String delay = line[ARR_DELAY_NEW];
            if (!delay.isEmpty()) {
                context.write(new AirportWritableComparable(airportId, FLIGHT_DATA_TYPE), new Text(delay));
            }
        }
    }
}
