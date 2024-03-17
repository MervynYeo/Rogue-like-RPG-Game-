package com.fit3077.covidtesting.app.common;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class FIT3077Api {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final String BASE_URL = "https://fit3077.com/api/v2";
    private static final String API_KEY = "N8QQBKLKdBcBTKcRhwzPtp79RpqLNg"; // don't commit your api key

    public static String get(String urlPath) throws IOException, InterruptedException {
        System.out.printf("GET %s\n", BASE_URL + urlPath);
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL + urlPath))
                .setHeader("Authorization", API_KEY)
                .GET()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        if (response.statusCode() == 200) {
            return response.body();
        } else {
            return null;
        }
    }

    public static String post(String urlPath, String jsonBody) throws IOException, InterruptedException {
        System.out.printf("POST %s\n", BASE_URL + urlPath + " " + jsonBody);
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL + urlPath))
                .setHeader("Authorization", API_KEY)
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .POST(Optional.ofNullable(jsonBody).map(HttpRequest.BodyPublishers::ofString).orElse(null))
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }

    public static String delete(String urlPath)throws IOException,InterruptedException{
        System.out.printf("DELETE %s\n", BASE_URL + urlPath );
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL + urlPath))
                .setHeader("Authorization", API_KEY)
                .DELETE()
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }

    public static String patch(String urlPath, String jsonBody) throws IOException, InterruptedException {
        System.out.printf("PATCH %s\n", BASE_URL + urlPath + " " + jsonBody);
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(BASE_URL + urlPath))
                .setHeader("Authorization", API_KEY)
                .method("PATCH", Optional.ofNullable(jsonBody).map(HttpRequest.BodyPublishers::ofString).orElse(null))
                .header("Content-Type","application/json") // This header needs to be set when sending a JSON request body.
                .build();
        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
}
