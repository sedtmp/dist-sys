public class Request {
    private String url;
    private int count;

    public Request(String url, String count) {
        this.url = url;
        this.count = Integer.parseInt(count);
    }

    public String getUrl() {
        return url;
    }

    public int getCount() {
        return count;
    }

    public boolean hasZeroCount() {
        return count == 0;
    }

    public void decreaceCount() {
        count--;
    }
}
