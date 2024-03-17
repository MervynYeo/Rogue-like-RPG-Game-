package com.fit3077.covidtesting.view.test;

import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingModel;
import com.fit3077.covidtesting.model.test.*;
import com.fit3077.covidtesting.model.testsite.TestLocation;
import com.fit3077.covidtesting.model.user.UserModel;

import java.util.List;
import java.util.Scanner;

public class TestView {
    private TestModel testModel;
    private BookingModel bookingModel;

    public TestView(TestModel testModel, BookingModel bookingModel, UserModel userModel) {
        this.testModel = testModel;
        this.bookingModel = bookingModel;
        this.userModel = userModel;
    }

    private UserModel userModel;

    private Scanner scanner = new Scanner(System.in);


    public TestView(TestModel testModel) {
        this.testModel = testModel;
    }

    public Test getTest() {
        System.out.println("Test ID: ");
        String testId = scanner.next();
        Test test = testModel.getTest(testId);
        return test;
    }

    public List<Test> getTests() {
        return this.testModel.getTests();
    }

    public String inputPatientId() {
        System.out.println("Patient ID: ");
        String patientId = scanner.next();
        return patientId;
    }

    public String inputIsNeedHomeRATKit() {
        System.out.println("Do you need RAT kit? (Y/N)");
        String ans = scanner.next();
        return ans;
    }

    public Booking getAndVerifyBooking() {
        System.out.println("SMS PIN: ");
        String smsPin = scanner.next();
        System.out.println("Customer ID: ");
        String customerId = scanner.next();
        Booking booking = bookingModel.verifyBooking(customerId, smsPin);
        if (booking.getStatus().equals("INITIATED")) { // dummy data only has this to test
            System.out.println("Booking verified");
            return booking;
        }
        return null;
    }

    public TestType inputRegisterTestMethod(Booking booking) {
        System.out.println("Choose the type of test: (retype any one of the below)");
        if (booking.getAdditionalInfo().getTestLocation() == TestLocation.HOME) {
            System.out.println(TestType.RAT);
        } else {
            for (TestType type : TestType.values()) {
                System.out.println(type);
            }
        }
        TestType chosenType = Enum.valueOf(TestType.class, scanner.next().toUpperCase());
        return chosenType;
    }

    public TestRequest inputTestRequest() {
        TestRequest testRequest = new TestRequest();
        String patientId;
        boolean isPatientExist;
        do {
            System.out.println("Patient ID: ");
            patientId = scanner.next();
            isPatientExist = this.userModel.getUser(patientId) != null;
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
            isAdministererExist = this.userModel.getUser(administererId) != null;
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
            isBookingExist = this.bookingModel.getBooking(bookingId) != null;
            if (!isBookingExist) {
                System.out.println("Booking not found");
            }
        } while (!isBookingExist);
        testRequest.setBookingId(bookingId);
        testRequest.setResult(ResultType.PENDING);
        testRequest.setStatus("INITIATED");
        testRequest.setNotes("Notes");
        testRequest.setAdditionalInfo(new TestAdditionalInfo());
        return testRequest;
    }

}
