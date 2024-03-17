package com.fit3077.covidtesting.model.user;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.model.booking.Booking;
import com.fit3077.covidtesting.app.role.RoleTypes;
import com.fit3077.covidtesting.app.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String id;
    private String givenName;
    private String familyName;
    private String userName;
    private String phoneNumber;
    private Boolean isCustomer;
    private Boolean isReceptionist;
    private Boolean isHealthcareWorker;
    private Map<String, String> additionalInfo;
    private Role role;
    private List<Booking> bookings;

    public void printDescription() {
        System.out.println("Username: "+userName+"\nRole: "+role.toString()+"\nBookings: "+bookings);
    }



    public void setRole(Role role){
        this.role = role;
    }
    public Role getRole(){return role;}

    public ArrayList<Enum<RoleTypes>> checkRole(){
        ArrayList<Enum<RoleTypes>> roles=new ArrayList<Enum<RoleTypes>>();
        if(getIsCustomer()){
            roles.add(RoleTypes.CUSTOMER);
        }
        if(getIsReceptionist()){
            roles.add(RoleTypes.RECEPTIONIST);
        }
        if(getIsHealthcareWorker()){
            roles.add(RoleTypes.HEALTHCAREWORKER);
        }
        if(bookings.size() > 0){
            roles.add(RoleTypes.PATIENT);
        }
        return roles;
    }
    public List<Action> getActions(){
        return role.getActions();
    }

}
