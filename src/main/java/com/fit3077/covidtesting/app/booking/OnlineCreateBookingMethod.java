package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingRequest;
import com.fit3077.covidtesting.model.testsite.TestLocation;
import com.fit3077.covidtesting.model.user.User;

public class OnlineCreateBookingMethod extends CreateBookingMethod {

    public OnlineCreateBookingMethod(BookingController bookingController) {
        super(bookingController);
    }

    @Override
    public Booking execute(User user) {
        BookingRequest bookingRequest = this.bookingController.inputBookingRequest(user);
        if (bookingRequest.getAdditionalInfo().getTestLocation() == TestLocation.HOME) {
            bookingRequest.getAdditionalInfo().setQrCode("Sample QR Code");
            bookingRequest.getAdditionalInfo().setUrl("Sample URL");
        }
        try {
            Booking booking = this.bookingController.createBooking(bookingRequest);
            System.out.println("SMS PIN: " + booking.getSmsPin());
            System.out.println("QR Code: " + booking.getAdditionalInfo().getQrCode());
            System.out.println("URL: " + booking.getAdditionalInfo().getUrl());
            return booking;
        } catch (Exception e) {
            String errorMessage = "Error in OnlineCreateBookingMethod.execute: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
