import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {
    private final Integer packageId;
    private final String jsScript;
    private final String functionTitle;
    private final Test test;

    @JsonCreator
    public TestMessage(
            @JsonProperty(Constants.PACKAGE_ID) Integer
    )
}
