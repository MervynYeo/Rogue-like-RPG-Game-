package com.fit3077.covidtesting.controller.user;

import com.fit3077.covidtesting.app.Console;
import com.fit3077.covidtesting.app.role.RoleFactory;
import com.fit3077.covidtesting.app.role.RoleTypes;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.model.user.UserModel;
import com.fit3077.covidtesting.view.user.UserView;

public class UserController {

    private UserView userView;

    private UserModel userModel;

    public UserController(UserView userView, UserModel userModel) {
        this.userView = userView;
        this.userModel = userModel;
    }

    public void login(RoleFactory roleFactory, Console console) throws Exception {
        User loginUser = this.userView.getUser();
        Enum<RoleTypes> roleType = this.userView.setLoginRole(loginUser);
        loginUser.setRole(roleFactory.getRole(roleType));
        console.listen(loginUser);
        System.out.println("LoginAction done for user: " + loginUser.getUserName());
    }


}
