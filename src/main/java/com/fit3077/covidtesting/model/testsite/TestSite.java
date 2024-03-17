package com.fit3077.covidtesting.model.testsite;

import com.fit3077.covidtesting.model.booking.Booking;
import lombok.Data;

import java.util.Date;

@Data
public class TestSite {
    private String id;
    private String name;
    private String description;
    private String websiteUrl;
    private String phoneNumber;
    private Address address;
    private Booking booking;
    private Date createdAt;
    private Date updatedAt;
    private TestSiteAdditionalInfo additionalInfo;

    @Override
    public String toString(){
        return "name: "+name+"\n"+address+"\n"+additionalInfo;
    }
}
