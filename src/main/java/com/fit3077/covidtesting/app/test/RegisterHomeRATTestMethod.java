package com.fit3077.covidtesting.app.test;

import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.test.TestAdditionalInfo;
import com.fit3077.covidtesting.model.test.TestRequest;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.user.User;

public class RegisterHomeRATTestMethod extends RegisterTestMethod {

    public RegisterHomeRATTestMethod(TestController testController) {
        super(testController);
    }

    @Override
    Test execute(User user) {
        TestRequest testRequest = this.testController.inputTestRequest();
        testRequest.setType(TestType.RAT);
        String isNeedHomeRATKit = this.testController.inputIsNeedHomeRATKit();
        if (isNeedHomeRATKit.equalsIgnoreCase("Y")) {
            testRequest.setAdditionalInfo(new TestAdditionalInfo(true, false));
        } else {
            testRequest.setAdditionalInfo(new TestAdditionalInfo(false, false));
        }
        try {
            Test test = this.testController.registerTest(testRequest);
            System.out.println("Test: " + test);
            return test;
        } catch (Exception e) {
            String errorMessage = "Error in RegisterHomeRATTestMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
