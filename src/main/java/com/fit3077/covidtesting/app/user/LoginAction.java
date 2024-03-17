package com.fit3077.covidtesting.app.user;

import com.fit3077.covidtesting.app.Console;
import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.controller.user.UserController;
import com.fit3077.covidtesting.model.user.User;
import com.fit3077.covidtesting.app.role.RoleFactory;

public class LoginAction extends Action {

    private UserController userController;
    private RoleFactory roleFactory;
    private Console console;

    public LoginAction(DependencyContainer dependencyContainer, RoleFactory roleFactory, Console console) {
        this.userController = dependencyContainer.getUserController();
        this.roleFactory = roleFactory;
        this.console = console;
    }


    public void execute(User user) throws Exception {
        this.userController.login(this.roleFactory, this.console);
    }

    public String displayChar(){
        return "l";
    }
    public String toString(){
        return "Login";
    }
}
