import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    private static final int AIRPORT_ID = 14;
    private static final int DELAY = 18;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException {
        String[] line = value.toString().split(",");
        String airportIdStr = line[DELAY];
        if (!airportIdStr.equals("\"DEST_AIRPORT_ID\"")) {
            int airportId = 
        }
    }
}
