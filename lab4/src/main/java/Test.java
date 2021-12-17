import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
    @JsonProperty(Constants.TEST_NAME)
    private final String testName;

    @JsonProperty(Constants.EXPECTED_RESULT)
    private final String expectedResult;

    @JsonProperty(Constants.PARAMS)
    private final ArrayList<Integer> params;

    @JsonProperty(Constants.RESULT)
    private final boolean result;

    @JsonCreator
    public Test(
            @JsonProperty(Constants.TEST_NAME) String testName,
            @JsonProperty(Constants.EXPECTED_RESULT) String expectedResult,
            @JsonProperty(Constants.PARAMS) ArrayList<Integer> params
    ) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.result = false;
    }

    public 
}
