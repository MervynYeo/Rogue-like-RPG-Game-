package com.fit3077.covidtesting.app.booking;

import java.util.List;

public interface BookingEventListener {
    void alert(String bookingId, BookingEventType bookingEventType, String testSiteId);

    List<String> getLogs(String testSiteId);
}
