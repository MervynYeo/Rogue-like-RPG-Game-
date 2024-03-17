package com.fit3077.covidtesting.controller.booking;

import com.fit3077.covidtesting.app.booking.*;
import com.fit3077.covidtesting.app.common.DateUtils;
import com.fit3077.covidtesting.app.common.JsonUtils;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingModel;
import com.fit3077.covidtesting.model.booking.BookingRequest;
import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.view.booking.BookingView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BookingController {
    private BookingView bookingView;
    private BookingModel bookingModel;

    private static final Map<String, TestType> SUGGESTION_FORMULA = new HashMap<>() {{
        put("Y", TestType.PCR);
        put("N", TestType.RAT);
    }};

    public BookingController(BookingView bookingView, BookingModel bookingModel) {
        this.bookingView = bookingView;
        this.bookingModel = bookingModel;
    }

    public void checkBookingStatus() {
        Booking booking = bookingView.getAndVerifyBooking();
        bookingView.checkBookingStatus(booking);
    }

    public void suggestTest() {
        Booking booking = bookingView.getAndVerifyBooking();
        TestType testType = bookingView.suggestTestType(SUGGESTION_FORMULA);
        Test test = new Test();
        test.setType(testType);
        booking.addCovidTest(test);
        String covidTestsString = JsonUtils.toJsonString(booking.getCovidTests());
        String bookingUpdate = String.format("{\"additionalInfo\":{\"covidTests\":%s}}", covidTestsString);
        Booking updatedBooking = this.bookingModel.updateBooking(booking.getId(), bookingUpdate);
        System.out.println("Updated Booking with suggested test type: " + updatedBooking);
    }

    public CreateBookingMethodType inputCreateBookingMethodType() {
        return this.bookingView.inputCreateBookingMethodType();
    }

    public Booking createBooking(BookingRequest bookingRequest) throws Exception {
        return this.bookingModel.createBooking(bookingRequest);
    }

    public BookingRequest inputBookingRequest(User user) {
        return this.bookingView.inputBookingRequest(user);
    }

    public void seeBookingNotification(User user, BookingEventListener bookingEventListener) {
        this.bookingView.seeBookingNotification(user, bookingEventListener);
    }
    public void modifyBooking(User user){
        this.bookingView.modifyBooking(user);
    }


    public void cancelBooking(User user){
        this.bookingView.cancelBooking(user);
    }
    public void deleteBooking(){
        this.bookingView.deleteBooking();
    }
}
