package org.example.hw10;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Json {
    public static void main(String[] args){
        try {
            List<String> lines = Files.readAllLines(Paths.get("file.txt"), Charset.defaultCharset());
            Gson gsn = new GsonBuilder().setPrettyPrinting().create();
            String custGsn = gsn.toJson(lines);
             Files.write(Paths.get("user.json"), custGsn.getBytes());


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
