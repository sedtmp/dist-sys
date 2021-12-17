import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestMessage {
    private final Integer packageId;
    private final String jsScript;
    private final String functionName;
    private final Test test;

    @JsonCreator
    public TestMessage(
            @JsonProperty(Constants.PACKAGE_ID) Integer packageId,
            @JsonProperty(Constants.JS_SCRIPT) String jsScript,
            @JsonProperty(Constants.FUNCTION_NAME) String functionName,
            @JsonProperty(Constants.TESTS) Test test
    ) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.test = test;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public
}
