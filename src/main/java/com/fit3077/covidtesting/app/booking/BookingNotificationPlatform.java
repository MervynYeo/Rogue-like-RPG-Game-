package com.fit3077.covidtesting.app.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingNotificationPlatform implements BookingEventListener {

    private Map<String, List<String>> logs = new HashMap<>();

    public BookingNotificationPlatform() {

    }

    @Override
    public void alert(String bookingId, BookingEventType bookingEventType, String testSiteId) {
        if (!logs.containsKey(testSiteId)) {
            this.logs.put(testSiteId, new ArrayList<>());
        }
        this.logs.get(testSiteId).add(String.format("%s Booking ID %s", bookingEventType, bookingId));
    }

    @Override
    public List<String> getLogs(String testSiteId) {
        return logs.get(testSiteId);
    }

}
