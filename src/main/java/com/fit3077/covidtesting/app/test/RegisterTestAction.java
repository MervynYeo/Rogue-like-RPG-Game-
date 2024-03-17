package com.fit3077.covidtesting.app.test;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingModel;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.test.TestModel;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.model.user.UserModel;
import lombok.Data;

@Data
public class RegisterTestAction extends Action {
    private TestModel testModel;
    private BookingModel bookingModel;
    private UserModel userModel;
    private RegisterTestMethod registerTestMethod;
    private RegisterTestMethodFactory registerTestMethodFactory = new RegisterTestMethodFactory();

    private TestController testController;

    public RegisterTestAction(DependencyContainer dependencyContainer) {
        this.testController = dependencyContainer.getTestController();
    }

    @Override
    public void execute(User user) {
        Booking booking = this.testController.getAndVerifyBooking();
        if (booking != null) {
            TestType testType = this.testController.inputRegisterTestMethod(booking);
            RegisterTestMethod registerTestMethod = this.registerTestMethodFactory.getRegisterTestMethod(
                    this.testController, testType, booking.getAdditionalInfo().getTestLocation()
            );
            System.out.println("HHH" + registerTestMethod);
            Test test = registerTestMethod.execute(user);
            System.out.println("Test registered by User " + user.getUserName() + ": " + test);
        }
    }

//    private void inputRegisterTestMethod() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("SMS PIN: ");
//        String smsPin = scanner.next();
//        System.out.println("Customer ID: ");
//        String customerId = scanner.next();
//        Booking booking = bookingSystem.verifyBooking(customerId, smsPin);
//        if (booking.getStatus().equals("INITIATED")) { // dummy data only has this to test
//            System.out.println("Booking verified");
//            System.out.println("Choose the type of test: (retype any one of the below)");
//            if (booking.getAdditionalInfo().getTestLocation() == TestLocation.HOME) {
//                System.out.println(TestType.RAT);
//            } else {
//                for (TestType type : TestType.values()) {
//                    System.out.println(type);
//                }
//            }
//            TestType chosenType = Enum.valueOf(TestType.class, scanner.next().toUpperCase());
//            this.setRegisterTestMethod(registerTestMethodFactory.getRegisterTestMethod(
//                    this.testSystem, this.bookingSystem, this.userSystem, chosenType,
//                    booking.getAdditionalInfo().getTestLocation()
//            ));
//        }
//
//    }

    @Override
    public String displayChar() {
        return "rt";
    }

    @Override
    public String toString() {
        return "Register test";
    }
}
