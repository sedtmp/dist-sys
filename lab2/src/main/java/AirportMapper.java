import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    public static final String QUOTES = "\"";
    public static final String EMPTY_STRING = "";

    public static final int FIRST_STR_IN_DATA = 0;
    public static final int AIRPORT_DATA_TYPE = 0;
    public static final String LINE_SPLITTER = ",[\"]";

    public static final int CODE_COLUMN = 0;
    public static final int DESCRIPTION_COLUMN = 1;

    private String removeQuotes(String value) {
        return value.replaceAll(QUOTES, EMPTY_STRING);
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split(LINE_SPLITTER);
        if (key.get() > FIRST_STR_IN_DATA) {
            String code = removeQuotes(line[CODE_COLUMN]);
            String description = removeQuotes(line[DESCRIPTION_COLUMN]);
            context.write(
                    new AirportWritableComparable(Integer.parseInt(code), AIRPORT_DATA_TYPE),
                    new Text(description)
            );
        }
    }
}
