package org.example.hw11;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streeeem {
    public static void main(String[] args) {
        List<String> names = List.of("Vasia", "Petia", "Jora", "Luda", "Katia", "Paraska");
//
        String result = IntStream.range(0, names.size())
                .filter(i-> (i % 2) !=0)
                .mapToObj(i -> ( i ) +". " + names.get(i))
                .collect(Collectors.joining(", "));
        System.out.println( result);







    }

}
