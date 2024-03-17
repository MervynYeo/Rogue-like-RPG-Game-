package com.fit3077.covidtesting.app.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    public static String stringToDate(String dateString) {
        try {
            TimeZone tz = TimeZone.getTimeZone("UTC");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            Date date = formatter.parse(dateString);
            String ISODate = df.format(date);
            return ISODate;
        } catch (Exception e) {
            String errorMessage = "Error in DateUtils.stringToDate: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }

    public static String dateToISODate(Date date) {
        try {
            SimpleDateFormat sdf;
            sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            sdf.setTimeZone(TimeZone.getTimeZone("CET"));
            String ISODate = sdf.format(date);
            return ISODate;
        } catch (Exception e) {
            String errorMessage = "Error in DateUtils.dateToISODate: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
