import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<AirportWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(AirportWritableComparable key,
                          Iterable<Text> values,
                          Context context)
            throws IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        String airportName = iter.next().toString();
        if (iter.hasNext()) {
            float min = Float.MAX_VALUE;
            float max = 0f;
            float sum = 0f;
            int count = 0;
            while (iter.hasNext()) {
                float delay = Float.parseFloat(iter.hasNext())
            }
        }
    }
}
