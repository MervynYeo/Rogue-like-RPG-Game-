package com.fit3077.covidtesting.model.booking;

import com.fit3077.covidtesting.app.booking.PreviousBooking;
import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.testsite.TestLocation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingAdditionalInfo {
    private String url;
    private String qrCode;
    private TestLocation testLocation;
    private String createdBy;
    private List<Test> covidTests = new ArrayList<>();
    private List<PreviousBooking> previousBookings=new ArrayList<>(0);

    public BookingAdditionalInfo() {

    }



    public BookingAdditionalInfo(TestLocation testLocation, String createdBy) {
        this.testLocation = testLocation;
        this.createdBy = createdBy;
    }

    public void addPreviousBooking(PreviousBooking previousBooking){
        if (previousBookings.size()>=3){
            previousBookings=previousBookings.subList(1, previousBookings.size());
        }
        previousBookings.add(previousBooking);
    }
}
