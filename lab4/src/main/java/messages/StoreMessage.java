package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import tests.Test;

public class StoreMessage {
    private final String PACKAGE_ID = "packageId";
    private final String TEST = "test";

    @JsonProperty(PACKAGE_ID)
    private final Integer packageId;

    @JsonProperty(TEST)
    private final Test test;

    @JsonCreator
    public StoreMessage (
            @JsonProperty(PACKAGE_ID) Integer packageId,
            @JsonProperty(TEST) Test test
    ) {
        this.packageId = packageId;
        this.test = test;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public Test getTests() {
        return test;
    }
}
