package messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import constants.Constants;
import tests.Test;

import java.util.ArrayList;

public class PackageMessage {
    @JsonProperty(Constants.PACKAGE_ID)
    private Integer packageId;

    @JsonProperty(Constants.JS_SCRIPT)
    private String jsScript;

    @JsonProperty(Constants.FUNCTION_NAME)
    private String functionName;

    @JsonProperty(Constants.TESTS)
    private ArrayList<Test> tests;

    @JsonCreator
    public PackageMessage(
        @JsonProperty(Constants.PACKAGE_ID) Integer packageId,
        @JsonProperty(Constants.JS_SCRIPT) String jsScript,
        @JsonProperty(Constants.FUNCTION_NAME) String functionName,
        @JsonProperty(Constants.TESTS) ArrayList<Test> tests
    ) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public ArrayList<Test> getTests() {
        return tests;
    }
}
