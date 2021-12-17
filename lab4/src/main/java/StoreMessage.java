import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class StoreMessage {
    private final String PACKAGE_ID = "packageId";
    private final String TEST = "test";

    @JsonProperty(PACKAGE_ID)
    private final Integer packageId;

    @JsonProperty(TEST)
    private final ArrayList<Test> tests;

    @JsonCreator
    public StoreMessage (
            @JsonProperty(PACKAGE_ID) Integer packageId,
            @JsonProperty(TEST) ArrayList<Test> tests
    ) {
        this.packageId = packageId;
        this.tests = tests;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}