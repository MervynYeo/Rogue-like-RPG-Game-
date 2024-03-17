package com.fit3077.covidtesting.view.booking;

import com.fit3077.covidtesting.app.booking.BookingEventListener;
import com.fit3077.covidtesting.app.booking.CreateBookingMethodType;
import com.fit3077.covidtesting.app.booking.PreviousBooking;
import com.fit3077.covidtesting.app.common.DateUtils;
import com.fit3077.covidtesting.app.common.JsonUtils;
import com.fit3077.covidtesting.model.booking.*;
import com.fit3077.covidtesting.model.test.TestType;
import com.fit3077.covidtesting.model.testsite.TestLocation;
import com.fit3077.covidtesting.model.testsite.TestSite;
import com.fit3077.covidtesting.model.testsite.TestSiteModel;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.model.user.UserModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BookingView {

    private BookingModel bookingModel;
    private TestSiteModel testSiteModel;
    private UserModel userModel;

    private Scanner scanner = new Scanner(System.in);

    public BookingView(BookingModel bookingModel, TestSiteModel testSiteModel, UserModel userModel) {
        this.bookingModel = bookingModel;
        this.testSiteModel = testSiteModel;
        this.userModel = userModel;
    }

    public BookingView(BookingModel bookingModel, TestSiteModel testSiteModel) {
        this.bookingModel = bookingModel;
        this.testSiteModel = testSiteModel;
    }

    public Booking getAndVerifyBooking() {
        Booking booking;
        do {
            System.out.println("Customer ID: ");
            String customerId = scanner.next();
            System.out.println("SMS PIN: ");
            String smsPin = scanner.next();
            booking = bookingModel.verifyBooking(customerId, smsPin);
        } while (booking == null);
        return booking;
    }

    public void checkBookingStatus(Booking booking) {
        System.out.println("Booking status: " + booking.getStatus());
    }

    public TestType suggestTestType(Map<String, TestType> SuggestionFormula) {
        String symptom;
        System.out.println("Press Y if you have severe symptoms");
        System.out.println("Press N if you have no symptom or just want to confirm if you have been infected");
        symptom = scanner.next();
        TestType testType = SuggestionFormula.get(symptom.toUpperCase());
        return testType;
    }

    public CreateBookingMethodType inputCreateBookingMethodType() {
        System.out.println("Test site ID: ");
        String testSiteId = scanner.next();
        List<CreateBookingMethodType> bookingMethods = getTestSiteBookingMethods(testSiteId);
        if (bookingMethods.size() == 0) { // assumption if the field on additionalInfo not properly appended
            bookingMethods = List.of(CreateBookingMethodType.values());
        }
        System.out.println("Choose booking method: (retype any one of the below)");
        for (CreateBookingMethodType type : bookingMethods) {
            System.out.println(type);
        }
        CreateBookingMethodType chosenType = Enum.valueOf(CreateBookingMethodType.class, scanner.next().toUpperCase());
        return chosenType;
    }

    public BookingRequest inputBookingRequest(User user) {
        String customerId;
        boolean isUserExist;
        do {
            System.out.println("Customer ID: ");
            customerId = scanner.next();
            isUserExist = this.userModel.getUser(customerId) != null;
            if (!isUserExist) {
                System.out.println("User not found");
            }
        } while (!isUserExist);
        String testingSiteId;
        boolean isTestSiteExist;
        do {
            System.out.println("Test site ID: ");
            testingSiteId = scanner.next();
            isTestSiteExist = this.testSiteModel.getTestSite(testingSiteId) != null;
            if (!isTestSiteExist) {
                System.out.println("Test site not found");
            }
        } while (!isTestSiteExist);
        System.out.println("Start time (dd-MMM-yyyy, e.g. 7-Jun-2013): ");
        String startTime = DateUtils.stringToDate(scanner.next());
        System.out.println(startTime);
        System.out.println("Notes: ");
        String notes = scanner.next();
        TestLocation testLocation = null;
        do {
            System.out.println("Test location: (retype any one of the below)");
            for (TestLocation location : TestLocation.values()) {
                System.out.println(location);
            }
            try {
                testLocation = Enum.valueOf(TestLocation.class, scanner.next().toUpperCase());
            } catch (Exception e) {
                System.out.println("Only these option(s) are available");
            }
        } while (testLocation == null);
        String createdBy = user.getUserName();
        BookingAdditionalInfo bookingAdditionalInfo = new BookingAdditionalInfo(testLocation, createdBy);
        return new BookingRequest(customerId, testingSiteId, startTime, notes,
                bookingAdditionalInfo
        );
    }

    private List<CreateBookingMethodType> getTestSiteBookingMethods(String testSiteId) {
        TestSite testSite = this.testSiteModel.getTestSite(testSiteId);
        if (testSite.getAdditionalInfo() != null && testSite.getAdditionalInfo().getBookingType() != null) {
            return testSite.getAdditionalInfo().getBookingType();
        }
        return Collections.emptyList();
    }

    public void seeBookingNotification(User user, BookingEventListener bookingEventListener) {
        String testSiteId;
        if (user.getAdditionalInfo() == null || user.getAdditionalInfo().get("testSiteId") == null) {
            System.out.println("Cannot find retrieve admin's test site ID. Please provide your test site ID: ");
            testSiteId = scanner.next();
        } else {
            testSiteId = user.getAdditionalInfo().get("testSiteId");
        }
        List<String> logs = bookingEventListener.getLogs(testSiteId);
        System.out.printf("==================== BOOKING NOTIFICATION AT TEST SITE ID %s====================\n", testSiteId);
        for (String log : logs) {
            System.out.println(log);
        }
    }

    public void deleteBooking(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("please provide customer id:");
        String id=scanner.next();
        System.out.println("please provide booking id or sms pin:");
        String verification=scanner.next();
        Booking booking= bookingModel.verifyBooking(id,verification);
        if (booking==null){
            System.out.println("Booking not found,please try again.");
        } else if (!booking.getStatus().equalsIgnoreCase(BookingStatus.Cancelled.toString())) {
            System.out.println("Please cancel the booking before deleting");

        } else {
            bookingModel.deleteBooking(booking);
        }
    }


    public void modifyBooking(User user){
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

    public void cancelBooking(User user){
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
            if (!booking.getStatus().equalsIgnoreCase(BookingStatus.Cancelled.toString())){
                String bookingUpdate = String.format("{\"status\":\"%s\"}",BookingStatus.Cancelled);
                Booking updatedBooking = this.bookingModel.updateBooking(booking.getId(), bookingUpdate);
            }
        }
    }
}
