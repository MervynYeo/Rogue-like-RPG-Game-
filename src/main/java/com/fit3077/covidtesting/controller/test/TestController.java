package com.fit3077.covidtesting.controller.test;

import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.JsonUtils;
import com.fit3077.covidtesting.app.common.ReceptionistCaller;
import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.test.TestModel;
import com.fit3077.covidtesting.model.test.TestRequest;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.testsite.TestLocation;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.view.test.TestView;

import java.util.List;
import java.util.Optional;

public class TestController {
    private TestView testView;
    private TestModel testModel;

    public TestController(TestView testView, TestModel testModel) {
        this.testView = testView;
        this.testModel = testModel;
    }

    public void scanQR() {
        Test test = testView.getTest();
        if (test.getBooking().getAdditionalInfo().getQrCode() != null) {
            if (canReceiveRATKit(test)) {
                test.getAdditionalInfo().setIsRATKitReceived(true);
                String additionalInfoFields = JsonUtils.toJsonString(test.getAdditionalInfo());
                String additionalInfo = String.format("{\"additionalInfo\":%s}", additionalInfoFields);
                System.out.println("additionalInfo " + additionalInfo);
                try {
                    Test updatedTest = testModel.updateTest(test.getId(), additionalInfo);
                    System.out.println(updatedTest);
                } catch (Exception e) {
                    System.out.println("Error in TestController.scanQR");
                }
            } else {
                System.out.println("Booking verified");
            }
        } else {
            System.out.println("Invalid QR Code");
        }
    }

    public void receiveRATKit(Action scanQRAction) {
        String patientId = this.testView.inputPatientId();
        List<Test> tests = this.testView.getTests();
        for (Test test : tests) {
            try {
                if (test.getPatient().getId().equals(patientId) && canReceiveRATKit(test)) {
                    User staff = ReceptionistCaller.getReceptionist();
                    System.out.println("Staff called: " + staff);
                    scanQRAction.execute(staff);
                    System.out.println("Scanned QR");
                }
            } catch (Exception e) {
                System.out.println("Error in ReceiveRATKitAction.isValid: " + e.getMessage());
            }
        }
        System.out.println("Received RAT kit");
    }

    private Boolean canReceiveRATKit(Test test) {
        return (test.getType() == TestType.RAT &&
                test.getBooking().getAdditionalInfo().getTestLocation() == TestLocation.HOME) ||
                (test.getType() == TestType.RAT && Optional.ofNullable(test.getAdditionalInfo().getIsNeedRATKit()).orElse(false));
    }

    public Test registerTest(TestRequest testRequest) throws Exception {
        return this.testModel.registerTest(testRequest);
    }


    public TestType inputRegisterTestMethod(Booking booking) {
        return this.testView.inputRegisterTestMethod(booking);
    }

    public TestRequest inputTestRequest() {
        return this.testView.inputTestRequest();
    }

    public Booking getAndVerifyBooking() {
        return this.testView.getAndVerifyBooking();
    }

    public String inputIsNeedHomeRATKit() {
        return this.testView.inputIsNeedHomeRATKit();
    }
}
