package com.fit3077.covidtesting.app.common;

import com.fit3077.covidtesting.app.booking.*;
import com.fit3077.covidtesting.controller.booking.BookingController;
import com.fit3077.covidtesting.model.booking.BookingModel;
import com.fit3077.covidtesting.controller.test.TestController;
import com.fit3077.covidtesting.model.test.TestModel;
import com.fit3077.covidtesting.view.test.TestView;
import com.fit3077.covidtesting.controller.testsite.TestSiteController;
import com.fit3077.covidtesting.model.testsite.TestSiteModel;
import com.fit3077.covidtesting.view.testsite.TestSiteView;
import com.fit3077.covidtesting.controller.user.UserController;
import com.fit3077.covidtesting.model.user.UserModel;
import com.fit3077.covidtesting.view.user.UserView;
import com.fit3077.covidtesting.view.booking.BookingView;
import lombok.Data;

import java.util.Collections;

@Data
public class DependencyContainer {
    private BookingEventListener bookingNotificationPlatform;
    private BookingEventManager bookingEventManager;
    private BookingModel bookingModel;
    private TestModel testModel;
    private UserModel userModel;
    private TestSiteModel testSiteModel;

    private BookingView bookingView;
    private TestView testView;
    private UserView userView;
    private TestSiteView testSiteView;

    private BookingController bookingController;
    private TestController testController;
    private UserController userController;
    private TestSiteController testSiteController;

    public DependencyContainer() {
        this.bookingNotificationPlatform = new BookingNotificationPlatform();
        this.bookingEventManager = new BookingEventManager(Collections.singletonList(this.bookingNotificationPlatform));
        this.bookingModel = new BookingModel(this.bookingEventManager);
        this.testModel = new TestModel();
        this.userModel = new UserModel();
        this.testSiteModel = new TestSiteModel();

        this.bookingView = new BookingView(this.bookingModel, this.testSiteModel, this.userModel);
        this.testView = new TestView(this.testModel, this.bookingModel, this.userModel);
        this.userView = new UserView(this.userModel);
        this.testSiteView = new TestSiteView(this.testSiteModel);

        this.bookingController = new BookingController(this.bookingView, this.bookingModel);
        this.testController = new TestController(this.testView, this.testModel);
        this.userController = new UserController(this.userView, this.userModel);
        this.testSiteController = new TestSiteController(this.testSiteView, this.testSiteModel);
    }
}
