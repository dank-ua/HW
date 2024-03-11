package org.example.hw13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.xml.stream.events.Comment;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DemoSaveComents {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final int USER_ID = 4;

    public static void main(String[] args) {
        try {
            String lastPostId = lastPostIdUser(USER_ID);
            List<Map<String, Object>> comments = getCommentForPost(USER_ID, lastPostId);
            List<Map<String,Object>> todos = getTodosUser(USER_ID);

            Gson gson = new Gson();
            String commentsJson = gson.toJson(comments);
            String todosJson = gson.toJson(todos);

            String fileName = "user-" + USER_ID + "-post-" + lastPostId + "-comments.json";
            String fileNemeTodos = "user-" + USER_ID+"-todos.json";

            writeFile(commentsJson,fileName);
            System.out.println("file save " + fileName);

            writeFile(todosJson, fileNemeTodos);
            System.out.println("file save " + fileNemeTodos);


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


            Gson gson = new Gson();
            Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
           List<Map<String,Object>> posts = gson.fromJson(response.toString(), listType);

           int maxId = posts.stream()
                   .mapToInt(post -> ((Double) post.get("id")).intValue())
                   .max()
                   .orElse(0);


            return String.valueOf(maxId);
        }
    }

    private static List<Map<String, Object>> getCommentForPost(int userId, String postId) throws IOException {

        String url = BASE_URL + "/posts/" + postId + "/comments";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200) {
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) {
                    response.append(scanner.nextLine());
                }

                Gson gson = new Gson();
                Type listType = new  TypeToken<List<Map<String,Object>>>() {}.getType();
                return gson.fromJson(response.toString(), listType);


            }
        } else {
            throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
        }
    }

    private static List<Map<String,Object>> getTodosUser(int userId) throws IOException{
        String url = BASE_URL + "/users/" + userId + "/todos";
        HttpURLConnection connection = (HttpURLConnection)  new URL(url).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == 200){
            try (Scanner scanner = new Scanner(connection.getInputStream())){
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()){
                    response.append(scanner.nextLine());
                }
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
                List<Map<String,Object>> todos = gson.fromJson(response.toString(), listType);

                return todos.stream()
                        .filter(todo-> !((Boolean) todo.get("completed")))
                        .collect(Collectors.toList());
            }

        }else {
            throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
        }
     }
    private static void writeFile(String content, String fileName) throws IOException{
        try (Writer writer = new FileWriter(fileName)){
            writer.write(content);

        }
    }

}
