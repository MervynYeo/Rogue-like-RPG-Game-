package com.fit3077.covidtesting.app.role;

import com.fit3077.covidtesting.app.common.Action;
import com.fit3077.covidtesting.app.common.DependencyContainer;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public abstract class Role {

    protected RoleTypes name;

    protected DependencyContainer dependencyContainer;

    protected List<Action> actions;

    public Role() {

    }

    public Role(DependencyContainer dependencyContainer) {
        this.dependencyContainer = dependencyContainer;
    }

    public Role(DependencyContainer dependencyContainer, List<Action> actions, RoleTypes name) {
        this.dependencyContainer = dependencyContainer;
        this.actions = actions;
        this.name = name;
    }


    public abstract String toString();

    public List<Action> getActions(){
        if (Collections.unmodifiableList(actions)==null){
            return new ArrayList<Action>();
        }
        return Collections.unmodifiableList(actions);
    }

    protected void addAction(Action action) {
        actions.add(action);
    }

}
