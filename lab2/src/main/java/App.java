import org.apache.hadoop.mapreduce.Job;

public class App {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage: App <flights> <airports> <output>");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(App.class);
        job.setJobName("");
    }
}
