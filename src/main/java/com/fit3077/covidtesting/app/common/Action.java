package com.fit3077.covidtesting.app.common;

import com.fit3077.covidtesting.model.user.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Action {

    public abstract void execute(User user) throws Exception;

    public abstract String displayChar();

    public abstract String toString();
}
