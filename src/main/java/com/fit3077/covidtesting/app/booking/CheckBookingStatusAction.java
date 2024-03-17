package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.user.User;

public class CheckBookingStatusAction extends Action {
    private BookingController bookingController;

    public CheckBookingStatusAction(DependencyContainer dependencyContainer) {
        this.bookingController = dependencyContainer.getBookingController();
    }

    @Override
    public void execute(User user) throws Exception {
        bookingController.checkBookingStatus();
    }

    @Override
    public String displayChar() {
        return "cbs";
    }

    @Override
    public String toString() {
        return "Check booking status";
    }
}
