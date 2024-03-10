package org.example.hw13;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DemoSaveComents {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final int USER_ID = 4;

    public static void main(String[] args) {
        try {
            String lastPostId = lastPostIdUser(USER_ID);
            String commentsJson = getCommentForPost(USER_ID);

            String fileName = "user-" + USER_ID + "-post-" + lastPostId + "-comments.json";
            writeFile(commentsJson,fileName);
            System.out.println("fileName = " + fileName);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String lastPostIdUser(int userId) throws IOException{
        String url = BASE_URL + "/users/" + userId + "/posts";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        try (Scanner scanner = new Scanner(connection.getInputStream())){
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()){
                response.append(scanner.nextLine());
            }

            int maxId = 0;
            String[] posts = response.toString().split("\\},\\s*\\{");
            for (String post : posts){
                int postId = Integer.parseInt(post.replaceAll("\\D+", ""));
                if (postId > maxId){
                    maxId = postId;
                }
            }
            return String.valueOf(maxId);
        }
    }

    private static String getCommentForPost(int userId) throws IOException {
        String lastPostId = lastPostIdUser(userId);
        String url = BASE_URL + "/posts/" + lastPostId + "/comments";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }
                return response.toString();
            }
        } else {
            throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
        }
    }
    private static void writeFile(String content, String fileName) throws IOException{
        try (Writer writer = new FileWriter(fileName)){
            writer.write(content);

        }
    }

}
