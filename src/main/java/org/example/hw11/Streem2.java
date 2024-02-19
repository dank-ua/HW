package org.example.hw11;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streem2 {
    public static void main(String[] args) {
        Stream<String> names = List.of("Vasia", "Petia", "Jora", "Luda", "Katia", "Paraska")
                .stream()
                .map(i -> i.toUpperCase())
                .sorted();
        List<String> result = names.collect(Collectors.toList());
        System.out.println(  result);
    }
}
