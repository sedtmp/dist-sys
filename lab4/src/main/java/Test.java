import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
    private final String TEST_NAME = "testName";
    private final String EXPECTED_RESULT = "expectedResult";
    private final String PARAMS = "params";
    private final String RESULT = "result";

    @JsonProperty(TEST_NAME)
    private final String testName;

    @JsonProperty(EXPECTED_RESULT)
    private final String expectedResult;

    @JsonProperty(PARAMS)
    private final ArrayList<Integer> params;

    @JsonProperty(RESULT)
    private final boolean result;

    @JsonCreator
    public Test(
            @JsonProperty(TEST_NAME) String testName,
            @JsonProperty(EXPECTED_RESULT) String expectedResult,
            @JsonProperty(PARAMS) ArrayList<Integer> params
    ) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = false;
    }

    public Test(TestMessage message) {
        this.testName = message.getTest()
    }
}
