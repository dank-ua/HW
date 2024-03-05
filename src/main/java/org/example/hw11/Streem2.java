package org.example.hw11;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streem2 {
    public static void main(String[] args) {
        String names = List.of("Vasia", "Petia", "Jora", "Luda", "Katia", "Paraska")
                .stream()
                .map(i -> i.toUpperCase())
                .sorted()
                .collect(Collectors.joining(", "));
       //   List<String> result = names.collect(Collectors.toList());
        System.out.println(  names);
    }
}
