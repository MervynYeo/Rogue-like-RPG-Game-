package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.user.User;
import lombok.Data;

@Data
public class CreateBookingAction extends Action {

    private BookingController bookingController;

    private CreateBookingMethodFactory createBookingMethodFactory = new CreateBookingMethodFactory();

    public CreateBookingAction(DependencyContainer dependencyContainer) {
        this.bookingController = dependencyContainer.getBookingController();
    }

    @Override
    public void execute(User user) {
        CreateBookingMethodType createBookingMethodType = this.bookingController.inputCreateBookingMethodType();
        CreateBookingMethod createBookingMethod = this.createBookingMethodFactory.getCreateBookingMethod(
                this.bookingController, createBookingMethodType
        );
        Booking booking = createBookingMethod.execute(user);
        System.out.println("Booking initiated by User " + user.getUserName() + ": " + booking + " & created by User " +
                booking.getAdditionalInfo().getCreatedBy()
        );
    }

    @Override
    public String displayChar() {
        return "cb";
    }

    @Override
    public String toString() {
        return "Create booking";
    }
}
