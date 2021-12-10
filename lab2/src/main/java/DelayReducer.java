import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class DelayReducer extends Reducer<AirportWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(AirportWritableComparable key,
                          Iterator<Text> values,
                          Context context)
            throws IOException, InterruptedException {
        Iterator<Text> iter = values.next().to
    }
}
