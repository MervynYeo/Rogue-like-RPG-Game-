package com.fit3077.covidtesting.app.test;

import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.test.TestRequest;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.user.User;

public class RegisterOnsiteTestMethod extends RegisterTestMethod {

    private TestType type;

    public RegisterOnsiteTestMethod(TestController testController, TestType type) {
        super(testController);
        this.type = type;
    }

    @Override
    Test execute(User user) {
        TestRequest testRequest = this.testController.inputTestRequest();
        testRequest.setType(this.type);
        try {
            System.out.println("Test request: " + testRequest);
            Test test = this.testController.registerTest(testRequest);
            System.out.println("Test: " + test);
            return test;
        } catch (Exception e) {
            String errorMessage = "Error in RegisterOnsiteTestMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }

    }
}
