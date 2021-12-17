package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import constants.Constants;
import tests.Test;

import java.util.ArrayList;

public class StoreMessage {
    @JsonProperty(Constants.PACKAGE_ID)
    private final Integer packageId;

    @JsonProperty(Constants.TEST)
    private final ArrayList<Test> tests;

    @JsonCreator
    public StoreMessage (
            @JsonProperty(Constants.PACKAGE_ID) Integer packageId,
            @JsonProperty(Constants.TEST) ArrayList<Test> tests
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
