package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.app.common.ReceptionistCaller;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingRequest;
import com.fit3077.covidtesting.app.role.RoleTypes;
import com.fit3077.covidtesting.model.user.User;

public class OnSiteCreateBookingMethod extends CreateBookingMethod {

    public OnSiteCreateBookingMethod(BookingController bookingController) {
        super(bookingController);
    }

    @Override
    public Booking execute(User user) {
        System.out.println("You are " + user.getRole().getName());
        if (user.getRole().getName() != RoleTypes.RECEPTIONIST) {
            System.out.println(
                    "You must be assisted by a receptionist to create booking.\n" +
                            "We will call one for you.\n" +
                            "In the meantime, please fill in your booking request information: "
            );
            user = ReceptionistCaller.getReceptionist();
        }
        BookingRequest bookingRequest = this.bookingController.inputBookingRequest(user);
        try {
            Booking booking = this.bookingController.createBooking(bookingRequest);
            System.out.println("SMS PIN: " + booking.getSmsPin());
            return booking;
        } catch (Exception e) {
            String errorMessage = "Error in OnSiteCreateBookingMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
