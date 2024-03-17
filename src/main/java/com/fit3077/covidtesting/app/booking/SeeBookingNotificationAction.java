package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.user.User;

public class SeeBookingNotificationAction extends Action {
    private BookingController bookingController;
    private BookingEventListener bookingNotificationPlatform;

    public SeeBookingNotificationAction(DependencyContainer dependencyContainer) {
        this.bookingController = dependencyContainer.getBookingController();
        this.bookingNotificationPlatform = dependencyContainer.getBookingNotificationPlatform();
    }

    public void execute(User user) {
        this.bookingController.seeBookingNotification(user, this.bookingNotificationPlatform);
    }

    @Override
    public String displayChar() {
        return "sbn";
    }

    @Override
    public String toString() {
        return "See booking notification platform";
    }
}
