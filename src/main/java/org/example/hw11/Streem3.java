package org.example.hw11;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Streem3 {
    public static void main(String[] args) {
        String[] array = {"1, 2, 0", "4, 5"};

        String digit = Arrays.stream(array)
                .collect(Collectors.joining(", "));

        Integer[] result = Arrays.stream(digit.split(", "))
                .map(Integer::parseInt)
                .sorted()
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(result));



    }
}
