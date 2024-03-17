package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingModel;
import com.fit3077.covidtesting.model.booking.BookingStatus;
import com.fit3077.covidtesting.model.user.User;

import java.util.Scanner;

public class CancelBooking extends Action {
     BookingController bookingController;

    public CancelBooking(DependencyContainer dependencyContainer){
        this.bookingController = dependencyContainer.getBookingController();
    }

    @Override
    public void execute(User user)  {
        bookingController.cancelBooking(user);
    }

    @Override
    public String displayChar() {
        return "cbb";
    }

    @Override
    public String toString() {
        return "Cancel Booking";
    }
}
