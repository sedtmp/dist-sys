import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Test implements Serializable {
    @JsonProperty("testName")
    private final String testName;

    @JsonProperty("expectedResult")
    private final String expectedResult;

    @JsonProperty("params")
    private final String params;

    @JsonProperty("result")
    private final String result;

    @JsonCreator
    public Test(String testName, )
}
