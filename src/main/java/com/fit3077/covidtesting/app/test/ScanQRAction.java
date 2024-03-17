package com.fit3077.covidtesting.app.test;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.user.User;

import java.util.Optional;

public class ScanQRAction extends Action {

    private TestController testController;

    public ScanQRAction(DependencyContainer dependencyContainer) {
        this.testController = dependencyContainer.getTestController();
    }

    @Override
    public void execute(User user) throws Exception {
        this.testController.scanQR();
    }

    private boolean isReceivingRATKit(Test test) {
        return test.getType() == TestType.RAT && Optional.ofNullable(test.getAdditionalInfo().getIsNeedRATKit()).orElse(false);
    }

    @Override
    public String displayChar() {
        return "sq";
    }

    @Override
    public String toString() {
        return "Scan QR (for RAT kit)";
    }
}