package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.user.User;
import lombok.Data;

@Data
public abstract class CreateBookingMethod {

    protected BookingController bookingController;

    public CreateBookingMethod(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    abstract Booking execute(User user);

}
