package com.fit3077.covidtesting.app.booking;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DateUtils;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.app.common.JsonUtils;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.model.booking.BookingAdditionalInfo;
import com.fit3077.covidtesting.model.booking.BookingModel;
import com.fit3077.covidtesting.model.testsite.TestSite;
import com.fit3077.covidtesting.model.testsite.TestSiteModel;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.model.user.UserModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ModifyBookingAction extends Action {
    private BookingModel bookingModel;
    private TestSiteModel testSiteModel;
    private UserModel userModel;

    private BookingController bookingController;

    public ModifyBookingAction(DependencyContainer dependencyContainer) {
        this.bookingController = dependencyContainer.getBookingController();
    }



    public ModifyBookingAction(BookingModel bookingModel, TestSiteModel testSiteModel, UserModel userModel){
        this.bookingModel = bookingModel;
        this.testSiteModel = testSiteModel;
        this.userModel = userModel;
    }

    public void execute(User user){
        Scanner scanner = new Scanner(System.in);
        String id=user.getId();
        String verification;
        if(!user.getRole().toString().equalsIgnoreCase("Customer role")){
            System.out.println("please provide customer id:");
            id=scanner.next();
        }
        System.out.println("please provide booking id or sms pin:");
        verification=scanner.next();
        Booking booking= bookingModel.verifyBooking(id,verification);
        if (booking==null){
            System.out.println("Booking not found,please try again.");
        }
        else {
            if(booking.getAdditionalInfo().getPreviousBookings().size()==0 || !promptRevert(scanner)){

               String date=promptDate(scanner);
               String newVenue=promptVenue(scanner);
               if (date.equalsIgnoreCase("fail")) {
                   System.out.println("fail");
                   return;
               }
               performModification(booking,DateUtils.stringToDate(date),newVenue);
            }
            else{
                revert(scanner,booking);
            }
        }
    }

    public void revert(Scanner scanner,Booking booking){
        List<PreviousBooking> previousBookingList=booking.getAdditionalInfo().getPreviousBookings();
        for (int i=0;i<previousBookingList.size();i++){
            System.out.println(i+". "+previousBookingList.get(i));
        }
        System.out.println("please enter the number of previous booking you wish to revert");
        int i =scanner.nextInt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        PreviousBooking previousBooking=previousBookingList.get(i);
        performModification(booking,previousBooking.getPerformDate().toString(),previousBooking.getVenue().getId());
    }

    public void performModification(Booking booking,String date,String testSite){
        BookingAdditionalInfo additionalInfo=booking.getAdditionalInfo();
        PreviousBooking previousBooking=new PreviousBooking(DateUtils.dateToISODate(booking.getStartTime()),booking.getTestingSite());
        additionalInfo.addPreviousBooking(previousBooking);
        String previous = JsonUtils.toJsonString(additionalInfo);
        if (date==null){
            DateUtils.dateToISODate(booking.getStartTime());
        }
        if(testSite.equalsIgnoreCase(null)){
            testSite=booking.getTestingSite().getId();
        }
        String bookingUpdate = String.format("{\"startTime\":\"%s\",\"testingSiteId\":\"%s\", \"additionalInfo\":%s}", date,testSite, previous);
        Booking updatedBooking = this.bookingModel.updateBooking(booking.getId(), bookingUpdate);
    }

    public boolean promptRevert(Scanner scanner){
        System.out.println("Do you wish to revert booking,enter Y if yes else N");
        String revert=scanner.next();
        if (revert.equalsIgnoreCase("Y")){
            return true;
        }
        return false;
    }

    public String promptVenue(Scanner scanner){
        System.out.println("please provide new testsite id,enter 'same' if same venue :");
        String newVenue=scanner.next();
        if(newVenue.equalsIgnoreCase("same")){
            return null;
        }
        return newVenue;
    }

    public String promptDate(Scanner scanner){
        System.out.println("please provide new date as DD-MMM-YYYY,enter 'same' if you wish not to change date :");
        String date=scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        if (!date.equalsIgnoreCase("same")){
            while (LocalDate.parse(date,formatter).isBefore(LocalDate.now())){
                System.out.println("invalid date please try again");
                date=scanner.next();
            }
            return date;
        }
        return null;
    }


    @Override
    public String displayChar() {
        return "mb";
    }

    @Override
    public String toString() {
        return "Modify Booking";
    }
}
