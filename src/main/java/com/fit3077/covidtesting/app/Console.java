package com.fit3077.covidtesting.app;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.user.LoginAction;
import com.fit3077.covidtesting.app.testsite.ViewTestSiteAction;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import com.fit3077.covidtesting.app.role.RoleFactory;
import com.fit3077.covidtesting.model.user.User;

import java.util.HashMap;
import java.util.Scanner;

public class Console {
    private User user;

    private DependencyContainer dependencyContainer;

    public Console(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    private void addAction(String hotKey,Action action,HashMap<String,Action> actions){
        actions.put(hotKey,action);
        System.out.println(hotKey+" : "+action);
    }

    private void setUser(User user) {
        this.user = user;
    }

    public void run() throws Exception {

        Scanner scanner = new Scanner(System.in);
        String c;
        do {
            System.out.println("Covid Testing Booking System");
            HashMap<String, Action> availableActions = new HashMap<>();
            ViewTestSiteAction viewTestSiteAction =new ViewTestSiteAction(dependencyContainer);
            addAction(viewTestSiteAction.displayChar(), viewTestSiteAction,availableActions);
            if (this.user == null) {
                RoleFactory roleFactory = new RoleFactory(dependencyContainer); // either should be here or Console
                LoginAction login = new LoginAction(dependencyContainer, roleFactory, this);
                addAction(login.displayChar(), login,availableActions);
            } else {
                for (Action action : user.getActions()) {
                    String command = action.displayChar();
                    addAction(command,action,availableActions);
                }
            }
            System.out.println("exit: Exit system");
            c = scanner.next();
            if (!c.equalsIgnoreCase("exit")) {
                if(c.equalsIgnoreCase("logout")){
                    user=null;
                }
                else {
                    try {
                        availableActions.get(c).execute(user);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        } while (!c.equalsIgnoreCase("exit"));
    }

    public void listen(User user) {
        if (this.user == null) {
            setUser(user);
        }
    }
}
