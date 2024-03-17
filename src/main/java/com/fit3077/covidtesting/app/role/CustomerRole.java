package com.fit3077.covidtesting.app.role;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import lombok.Data;

import java.util.List;

@Data
public class CustomerRole extends Role {

    public CustomerRole(DependencyContainer dependencyContainer) {
        super(dependencyContainer);
    }

    public CustomerRole(DependencyContainer dependencyContainer, List<Action> actions, RoleTypes name) {
        super(dependencyContainer, actions, name);
    }

    public  String toString(){
        return "Customer Role";
    }
}
