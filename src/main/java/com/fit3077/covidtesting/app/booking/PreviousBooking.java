package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.model.testsite.TestSite;
import lombok.Data;

@Data
public class PreviousBooking {
    private String performDate;
    private TestSite venue;

    public PreviousBooking(){

    }
    public PreviousBooking(String performDate,TestSite venue){
        this.performDate=performDate;
        this.venue=venue;
    }
}
