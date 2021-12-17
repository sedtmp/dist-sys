package tests;

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

    @JsonCreator
    public Test (
            @JsonProperty(Constants.TEST_NAME) String testName,
            @JsonProperty(Constants.EXPECTED_RESULT) String expectedResult,
            @JsonProperty(Constants.PARAMS) ArrayList<Integer> params
    ) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }
}