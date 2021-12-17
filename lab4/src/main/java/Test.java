import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {
    private final 

    @JsonProperty("testName")
    private final String testName;

    @JsonProperty("expectedResult")
    private final String expectedResult;

    @JsonProperty("params")
    private final ArrayList<Integer> params;

    @JsonProperty("result")
    private final boolean result;

    @JsonCreator
    public Test(String testName, String expectedResult, ArrayList<Integer> params) {
        this.
    }
}
