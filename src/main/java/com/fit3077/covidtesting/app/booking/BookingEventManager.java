package com.fit3077.covidtesting.app.booking;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingEventManager {
    private List<BookingEventListener> bookingEventListeners = new ArrayList<>();

    public BookingEventManager() {

    }

    public BookingEventManager(List<BookingEventListener> bookingEventListeners) {
        this.bookingEventListeners = bookingEventListeners;
    }

    public void subscribe(BookingEventListener bookingEventListener) {
        this.bookingEventListeners.add(bookingEventListener);
    }

    public void unsubscribe(BookingEventListener bookingEventListener) {
        this.bookingEventListeners.remove(bookingEventListener);
    }

    public void alert(String bookingId, BookingEventType bookingEventType, String testSiteId) {
        for (BookingEventListener listener : this.bookingEventListeners) {
            listener.alert(bookingId, bookingEventType, testSiteId);
        }
    }
}
