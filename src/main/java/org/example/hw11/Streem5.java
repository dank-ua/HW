package org.example.hw11;

import java.util.Iterator;
import java.util.stream.Stream;

public class Streem5 {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iter1 = first.iterator();
        Iterator<T> iter2 = second.iterator();

        Stream.Builder<T> builder = Stream.builder();

        while (iter1.hasNext() && iter2.hasNext()) { //hasNext() перевіряє чи є наступний елемент (тру/фолс)
            builder.accept(iter1.next()); // accept(T t) додає т до об'єкта Т
            builder.accept(iter2.next());
        }
        return builder.build();


    }

    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> stream2 = Stream.of(5, 4, 3, 2, 1);

        Stream<Integer> result = zip(stream1, stream2);

        result.forEach(System.out::println);

    }
}
