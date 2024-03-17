package com.fit3077.covidtesting.model.booking;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fit3077.covidtesting.app.booking.BookingEventManager;
import com.fit3077.covidtesting.app.booking.BookingEventType;
import com.fit3077.covidtesting.app.common.FIT3077Api;
import com.fit3077.covidtesting.app.common.JsonUtils;
import com.fit3077.covidtesting.model.Model;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class BookingModel extends Model {
    private static final String URL_PATH = "/booking";

    private BookingEventManager bookingEventManager;

    public BookingModel() {
        super.setUrlPath(URL_PATH);
    }

    public BookingModel(BookingEventManager bookingEventManager) {
        super.setUrlPath(URL_PATH);
        this.bookingEventManager = bookingEventManager;
    }

    public List<Booking> getBookings() {
        try {
            String response = FIT3077Api.get(this.urlPath);
            List<Booking> bookings = JsonUtils.toObjectList(response, new TypeReference<List<Booking>>() { });
            return bookings;
        } catch (Exception e) {
            String errorMessage = "Error in BookingSystem.getBookings: " + e.getMessage();
            System.out.println(errorMessage);
            return Collections.emptyList();
        }
    }
    public Booking updateBooking(String bookingId, String bookingUpdate){
        try {
            String response = FIT3077Api.patch(this.urlPath + "/" + bookingId, bookingUpdate);
            Booking booking = JsonUtils.toObject(response, Booking.class);
            this.bookingEventManager.alert(bookingId, BookingEventType.UPDATE, booking.getTestingSite().getId());
            return booking;
        } catch (Exception e) {
            String errorMessage = "Error in BookingSystem.updateBooking: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    public String deleteBooking(Booking booking){
        try{
            this.bookingEventManager.alert(booking.getId(), BookingEventType.DELETE, booking.getTestingSite().getId());
            return FIT3077Api.delete(this.urlPath+"/"+booking.getId());
        }catch (Exception e){
            String errorMessage="Error in BookingSystem.deleteBooking: "+e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    public Booking createBooking(BookingRequest bookingRequest) throws Exception {
        try {
            System.out.println(JsonUtils.toJsonString(bookingRequest));
            String response = FIT3077Api.post(this.urlPath, JsonUtils.toJsonString(bookingRequest));

            Booking booking = JsonUtils.toObject(response, Booking.class);
            this.bookingEventManager.alert(booking.getId(), BookingEventType.CREATE, booking.getTestingSite().getId());
            return booking;
        } catch (Exception e) {
            String errorMessage = "Error in BookingSystem.createBooking: " + e.getMessage();
            System.out.println(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    public String checkBookingStatus(String customerId, String smsPin) {
        List<Booking> bookings = getBookings();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getId().equals(customerId) && booking.getSmsPin().equals(smsPin)) {
                return booking.getStatus();
            }
        }
        return null;
    }

    public Booking getBooking(String id) {
        try {
            String response = FIT3077Api.get(this.urlPath + "/" + id);
            Booking booking = JsonUtils.toObject(response, Booking.class);
            return booking;
        } catch (Exception e) {
            String errorMessage = "Error in BookingSystem.getBooking: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    public Booking verifyBooking(String customerId, String smsPin) {
        List<Booking> bookings = getBookings();
        for (Booking booking : bookings) {
            if (booking.getCustomer().getId().equals(customerId) && (booking.getSmsPin().equals(smsPin) || booking.getId().equals(smsPin))) {
                return booking;
            }
        }
        return null;
    }
}
