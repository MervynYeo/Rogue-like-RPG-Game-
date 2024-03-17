package com.fit3077.covidtesting.model.test;

import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class Test {
    private String id;
    private TestType type;
    private User patient;
    private User administerer;
    private Booking booking;
    private ResultType result;
    private String status;
    private String notes;
    private Date datePerformed;
    private Date dateOfResults;
    private Date createdAt;
    private Date updatedAt;
    private TestAdditionalInfo additionalInfo;

    public Test(User user,User administerer,Booking booking,TestType type){
        this.patient=user;
        this.administerer=administerer;
        this.booking=booking;
        this.type=type;
        this.status="Created";
        this.result= ResultType.PENDING;
        this.notes="";
    }
}
