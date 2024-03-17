package com.fit3077.covidtesting.app.test;

import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingModel;
import com.fit3077.covidtesting.model.test.*;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.model.user.UserModel;

import java.util.Scanner;

public abstract class RegisterTestMethod {
    protected TestModel testModel;
    protected BookingModel bookingModel;
    protected UserModel userModel;

    protected TestController testController;

    public RegisterTestMethod(TestController testController) {
        this.testController = testController;
    }

    abstract Test execute(User user);

    protected User getUser(String id) {
        return this.userModel.getUser(id);
    }

    protected boolean isUserExist(String id) {
        if (getUser(id) == null) {
            return false;
        }
        return true;
    }

    protected Booking getBooking(String id) {
        return this.bookingModel.getBooking(id);
    }

    protected boolean isBookingExist(String id) {
        if (getBooking(id) == null) {
            return false;
        }
        return true;
    }

    protected Test registerTest(TestRequest testRequest) throws Exception {
        return this.testModel.registerTest(testRequest);
    }

    protected void inputTestRequest(TestRequest testRequest) {
        Scanner scanner = new Scanner(System.in);
        String patientId;
        boolean isPatientExist;
        do {
            System.out.println("Patient ID: ");
            patientId = scanner.next();
            isPatientExist = isUserExist(patientId);
            if (!isPatientExist) {
                System.out.println("Patient not found");
            }
        } while (!isPatientExist);
        testRequest.setPatientId(patientId);
        String administererId;
        boolean isAdministererExist;
        do {
            System.out.println("Administerer ID: ");
            administererId = scanner.next();
            isAdministererExist = isUserExist(administererId);
            if (!isAdministererExist) {
                System.out.println("Administerer not found");
            }
        } while (!isAdministererExist);
        testRequest.setAdministererId(administererId);
        String bookingId;
        boolean isBookingExist;
        do {
            System.out.println("Booking ID: ");
            bookingId = scanner.next();
            isBookingExist = isBookingExist(bookingId);
            if (!isBookingExist) {
                System.out.println("Booking not found");
            }
        } while (!isBookingExist);
        testRequest.setBookingId(bookingId);
        testRequest.setResult(ResultType.PENDING);
        testRequest.setStatus("INITIATED");
        testRequest.setNotes("Notes");
        testRequest.setAdditionalInfo(new TestAdditionalInfo());
    }
}
