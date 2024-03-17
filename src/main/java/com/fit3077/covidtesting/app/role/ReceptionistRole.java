package com.fit3077.covidtesting.app.role;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ReceptionistRole extends Role {

    public ReceptionistRole(DependencyContainer dependencyContainer, List<Action> actions, RoleTypes name) {
        super(dependencyContainer, actions, name);
    }

    public  String toString(){
        return "Receptionist Role";
    }
}
