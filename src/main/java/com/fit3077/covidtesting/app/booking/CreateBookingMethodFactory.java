package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.controller.booking.BookingController;

public class CreateBookingMethodFactory {
    public CreateBookingMethod getCreateBookingMethod(
            BookingController bookingController,
            CreateBookingMethodType type
    ) {
        CreateBookingMethod method = null;
        switch (type) {
            case ONLINE:
                method = new OnlineCreateBookingMethod(bookingController);
                break;
            case ONSITE:
                method = new OnSiteCreateBookingMethod(bookingController);
                break;
            default:
                break;
        }
        return method;
    }
}
