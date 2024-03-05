package org.example.hw11;

import java.util.stream.Stream;

public class Streem4 {
    public static Stream<Long> random(long a, long c, long m, long seed){
        return Stream.iterate(seed, x -> (a * x +c ) % m);
    }
    public static void main(String[] args) {
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2 , 48);
        long seed = 0;

        Stream<Long> digit = random(a, c, m, seed)
                .limit(69);


        digit.forEach(System.out:: println);
    }
}
