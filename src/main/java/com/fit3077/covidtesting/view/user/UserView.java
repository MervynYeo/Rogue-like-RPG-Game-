package com.fit3077.covidtesting.view.user;

import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.app.role.RoleTypes;
import com.fit3077.covidtesting.model.user.UserModel;

import java.util.ArrayList;
import java.util.Scanner;

public class UserView {
    private UserModel userModel;

    private Scanner scanner = new Scanner(System.in);

    public UserView(UserModel userModel) {
        this.userModel = userModel;
    }

    public User getUser() throws Exception {
        System.out.println("Please enter your username:");
        String username = scanner.next();
        System.out.println("Please enter your password:");
        String password = scanner.next();
        try {
            User user = userModel.login(username, password);
            return user;
        } catch (Exception e) {
            String errorMessage = "Failed to log in, error in LoginAction.execute: " + e.getMessage();
            System.out.println(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    public Enum<RoleTypes> setLoginRole(User user) {
        ArrayList<Enum<RoleTypes>> roles=user.checkRole();
        for(int i = 0; i < roles.size(); i++) {
            System.out.println("press "+i+" for "+roles.get(i));
        }
        System.out.println("Please select an account to login:");
        Integer index =scanner.nextInt();
        return roles.get(index);
    }
}
