package com.fit3077.covidtesting.model.testsite;

import com.fit3077.covidtesting.app.booking.CreateBookingMethodType;
import lombok.Data;

import java.util.List;

@Data
public class TestSiteAdditionalInfo {

    private String facilityType;
    private boolean isOpen;
    private String waitingTime;
    private List<CreateBookingMethodType> bookingType;

//    @Override
//    public String toString(){
//        String ret="";
//        ret+="Type of facility: "+facilityType+"\n";
//        if(isOpen){
//            ret+="Facility is open";
//        }
//        else {
//            ret+="Facility is closed";
//        }
//
//        ret+="\nwaiting time: "+waitingTime;
//        return ret;
//    }
}
