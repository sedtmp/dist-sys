import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Test implements Serializable {
    @JsonProperty("testName")
    private final String testName;

    @JsonProperty("expectedResult")
    private final String expectedResult;

    @JsonProperty("testName")
    private final String testName;

    @JsonProperty("testName")
    private final String testName;
}
