package com.fit3077.covidtesting.model.booking;

import com.fit3077.covidtesting.model.test.Test;
import com.fit3077.covidtesting.model.testsite.TestSite;
import com.fit3077.covidtesting.model.user.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Booking {
    private String id;
    private User customer;
    private TestSite testingSite;
    private Date createdAt;
    private Date updatedAt;
    private Date startTime;
    private String smsPin;
    private String status;
    private List<Test> covidTests;
    private String notes;
    private BookingAdditionalInfo additionalInfo;

    public void addCovidTest(Test test){
        if (covidTests==null){
            covidTests=new ArrayList<Test>();
        }
        covidTests.add(test);
    }
}
