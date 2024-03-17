package com.fit3077.covidtesting.app.role;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import lombok.Data;

import java.util.List;

@Data
public class PatientRole extends Role {

    public PatientRole(DependencyContainer dependencyContainer, List<Action> actions, RoleTypes name) {
        super(dependencyContainer, actions, name);
    }

    public  String toString(){
        return "Patient Role";
    }
}
