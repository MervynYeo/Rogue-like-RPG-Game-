package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.user.User;

public class SuggestTestAction extends Action {

    private BookingController bookingController;

    public SuggestTestAction(DependencyContainer dependencyContainer) {
        this.bookingController = dependencyContainer.getBookingController();
    }

    public void execute(User user) throws Exception {
        this.bookingController.suggestTest();
    }

    public  String displayChar() {
        return "s";
    };

    public  String toString(){
     return "Suggest Test";
    }
}
