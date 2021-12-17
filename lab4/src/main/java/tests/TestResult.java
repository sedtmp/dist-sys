package tests;

import tests.Test;

public class TestResult {
    private final Test test;
    private final String result;

    public TestResult(Test test, String result) {
        this.test = test;
        this.result = result;
    }

    public Test getTest() {
        return test;
    }

    public String getResult() {
        return result;
    }
}
