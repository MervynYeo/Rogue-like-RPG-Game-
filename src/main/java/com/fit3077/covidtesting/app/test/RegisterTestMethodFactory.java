package com.fit3077.covidtesting.app.test;

import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.testsite.TestLocation;

public class RegisterTestMethodFactory {
    public RegisterTestMethod getRegisterTestMethod(
            TestController testController,
            TestType type, TestLocation testLocation
    ) {
        if (testLocation == TestLocation.HOME) {
            return new RegisterHomeRATTestMethod(testController);
        } else {
            return new RegisterOnsiteTestMethod(testController, type);
        }
    }
}
