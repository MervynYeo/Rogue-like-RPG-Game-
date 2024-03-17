package com.fit3077.covidtesting.app.common;

import com.fit3077.covidtesting.app.role.ReceptionistRole;
import com.fit3077.covidtesting.model.user.User;

import java.util.HashMap;
import java.util.UUID;

public class ReceptionistCaller {
    public static User getReceptionist() {
        return new User(
                UUID.randomUUID().toString(),
                "Helper",
                "Receptionist",
                "helperReceptionist",
                null,
                false,
                true,
                false,
                new HashMap<String, String>(),
                new ReceptionistRole(),
                null
        );
    }
}
