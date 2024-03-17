package com.fit3077.covidtesting.app.role;

import com.fit3077.covidtesting.app.booking.*;
import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.app.test.ReceiveRATKitAction;
import com.fit3077.covidtesting.app.test.RegisterTestAction;
import com.fit3077.covidtesting.app.test.ScanQRAction;

import java.util.Arrays;
import java.util.List;

public class RoleFactory {
    private DependencyContainer dependencyContainer;
    public RoleFactory(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    public Role getRole(Enum<RoleTypes> role) {
        if(role == null){
            return null;
        } else if (role == RoleTypes.CUSTOMER) {
            List<Action> customerActions = Arrays.asList(
                    new CreateBookingAction(dependencyContainer),
                    new CheckBookingStatusAction(dependencyContainer),
                    new ModifyBookingAction(dependencyContainer),
                    new CancelBooking(dependencyContainer)
            );
            return new CustomerRole(dependencyContainer, customerActions, RoleTypes.CUSTOMER);
        } else if (role == RoleTypes.RECEPTIONIST) {
            List<Action> receptionistActions = Arrays.asList(
                    new CreateBookingAction(dependencyContainer),
                    new CheckBookingStatusAction(dependencyContainer),
                    new ScanQRAction(dependencyContainer),
                    new SeeBookingNotificationAction(dependencyContainer),
                    new ModifyBookingAction(dependencyContainer),
                    new DeleteBookingAction(dependencyContainer),
                    new CancelBooking(dependencyContainer)
            );
            return new ReceptionistRole(dependencyContainer,receptionistActions, RoleTypes.RECEPTIONIST);
        } else if (role == RoleTypes.HEALTHCAREWORKER) {
            List<Action> HealthCareActions = Arrays.asList(
                    new SuggestTestAction(dependencyContainer),
                    new CheckBookingStatusAction(dependencyContainer),
                    new RegisterTestAction(dependencyContainer),
                    new ScanQRAction(dependencyContainer)
            );
            return new HealthcareWorkerRole(dependencyContainer, HealthCareActions, RoleTypes.HEALTHCAREWORKER);
        } else if(role == RoleTypes.PATIENT){
            List<Action> patientActions = Arrays.asList(
                    new CheckBookingStatusAction(dependencyContainer),
                    new RegisterTestAction(dependencyContainer),
                    new ReceiveRATKitAction(dependencyContainer, new ScanQRAction(dependencyContainer))
            );
            return new PatientRole(dependencyContainer, patientActions, RoleTypes.PATIENT);
        }
        return null;
    }
}
