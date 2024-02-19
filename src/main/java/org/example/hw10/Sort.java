package org.example.hw10;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Sort {
    public static void main(String[] args) {
        try {
            List<String> line = Files.readAllLines(Paths.get("words.txt"), Charset.defaultCharset() );
            Map<String, Integer> map = new HashMap<>();
            String oneLine = String.join(" ", line);
            String[] words = oneLine.split("\\s+");

            for (String word : words){
                map.put(word, map.getOrDefault(word,0) + 1);
            }
            List<Map.Entry<String, Integer> >list = new ArrayList<>(map.entrySet());

            list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
            //comparingByValue за замовченням сортує від меншого до більшого

            for (Map.Entry<String, Integer> entr : list){
                System.out.println(entr.getKey() + " " + entr.getValue());
            }


        }catch (IOException e){
            System.out.println( e.getMessage());
        }
    }
}
