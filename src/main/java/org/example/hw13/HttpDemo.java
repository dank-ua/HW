package org.example.hw13;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class HttpDemo {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    private final HttpClient httpClient;

    public HttpDemo() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public  String newUser(String body) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >=200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new RuntimeException("Failed: " + response.statusCode());
        }
    }

    public  String updateUser(int userId, String jsonBody) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/" + userId))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >=200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new RuntimeException("Failed: " + response.statusCode());
        }


    }

    public void deleteUser(int userId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL+ "/"+ userId))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >=200 && response.statusCode() < 300) {
            System.out.println("DELETE");
        } else {
            throw new RuntimeException("Failed: " + response.statusCode());
        }

    }

    public String getAllUser() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >=200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new RuntimeException("Failed: " + response.statusCode());
        }
    }

    public String getUserById(int userId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + "/" + userId))
                .header("Content-Type", "application/json" )
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >=200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new RuntimeException("Failed: " + response.statusCode());
        }
    }

    public String getUserByName(String username) throws Exception{
        Map<String, String> param = new HashMap<>();
        param.put("username", username);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + buildString(param)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() >=200 && response.statusCode() < 300) {
            return response.body();
        } else {
            throw new RuntimeException("Failed: " + response.statusCode());
        }
    }

    private String buildString(Map<String, String> parametres){
        if (parametres.isEmpty()){
            return "";
        }
        StringBuilder newString = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parametres.entrySet()) {
            newString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return newString.substring(0,newString.length()-1);

    }

    public static void main(String[] args) throws Exception {
         HttpDemo apiClient =new  HttpDemo();

         String newUserName = "{ \"name\": \"Vasia Pupkin\", \"username\": \"vasopup\", \"email\": \"vaspup@gmail.com\" }";;
         String createdUser = apiClient.newUser(newUserName);
        System.out.println("createdUser = " + createdUser);

        int userIdToUpdate = 3;
        String updateUserJ= "{\"name\":\"Updated Name\",\"username\":\"updatedusername\",\"email\":\"updated@example.com\"}";
        String updateUser = apiClient.updateUser(userIdToUpdate,updateUserJ);
        System.out.println("updateUser = " + updateUser);

        int userIdDel = 4;
        apiClient.deleteUser(userIdDel);
        System.out.println("user "+  + userIdDel + " delete");

        String allUser = apiClient.getAllUser();
        System.out.println("allUser = " + allUser);

        int userIdFind = 2;
        String userId = apiClient.getUserById(userIdFind);
        System.out.println("userId = " + userId);

        String userName = "Antonette";
        String userByUsername = apiClient.getUserByName(userName);
        System.out.println("userByUsername  Antonette = " + userByUsername);
    }

}
