package com.fit3077.covidtesting.app.test;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.user.User;

public class ReceiveRATKitAction extends Action {

    private TestController testController;
    private Action scanQRAction;


    public ReceiveRATKitAction(DependencyContainer dependencyContainer, ScanQRAction scanQRAction) {
        this.testController = dependencyContainer.getTestController();
        this.scanQRAction = scanQRAction;
    }

    @Override
    public void execute(User user) throws Exception {
        this.testController.receiveRATKit(scanQRAction);
    }

    @Override
    public String displayChar() {
        return "rrk";
    }

    @Override
    public String toString() {
        return "Receive RAT kit";
    }
}
