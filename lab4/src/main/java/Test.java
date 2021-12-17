import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Test implements Serializable {
    @JsonProperty("testName")
    private final String testName;
}
