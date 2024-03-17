package com.fit3077.covidtesting.model.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fit3077.covidtesting.app.common.FIT3077Api;
import com.fit3077.covidtesting.app.common.JsonUtils;
import com.fit3077.covidtesting.model.Model;

import java.util.Base64;

public class UserModel extends Model {
    private static final String URL_PATH = "/user";

    public UserModel() {
        super.setUrlPath(URL_PATH);
    }

    public User login(String username, String password) throws Exception {
        String jsonString = "{" +
                "\"userName\":\"" +  username+ "\"," +
                "\"password\":\"" + password + "\"" +
                "}";
        try {
            String response = FIT3077Api.post(URL_PATH + "/login?jwt=true", jsonString);
            ObjectNode jsonNode = new ObjectMapper().readValue(response, ObjectNode.class);
            String token = jsonNode.get("jwt").textValue();
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String header = new String(decoder.decode(chunks[0]));
            String payload = new String(decoder.decode(chunks[1]));
            ObjectNode userNode = new ObjectMapper().readValue(payload, ObjectNode.class);
            String userId = userNode.get("sub").textValue();
            User user = getUser(userId);
            System.out.println("Account retrieved for User: " + user.getUserName());
            return user;
        } catch (Exception e) {
            String errorMessage = "Error in UserSystem.login: " + e.getMessage();
            System.out.println(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    public User getUser(String id) {
        try {
            String response = FIT3077Api.get(this.urlPath + "/" + id + "?fields=bookings");
            User user= JsonUtils.toObject(response, User.class);
            return user;
        } catch (Exception e) {
            String errorMessage = "Error in UserSystem.getUser: " + e.getMessage();
            System.out.println(errorMessage);
            return null;
        }
    }
}
