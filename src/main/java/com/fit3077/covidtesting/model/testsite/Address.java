package com.fit3077.covidtesting.model.testsite;

import lombok.Data;

import java.util.Map;

@Data
public class Address {
    private int latitude;
    private int longitude;
    private String unitNumber;
    private String street;
    private String street2;
    private String suburb;
    private String state;
    private Map<String, String> additionalInfo;

    @Override
    public String toString(){
        String ret="Address : "+unitNumber+", "+street+", ";
        if (street2!=null){
            ret+=street2+",";
        }
        ret+=suburb+", "+state;
        return ret;
    }
}
