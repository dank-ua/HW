package org.example.hw12.fiz;

public class StartFiz {
    public static void main(String[] args) {
        Fiybas fiybas = new Fiybas(15);
        Thread fizThread = new Thread(() -> {

            try {
                fiybas.fizz(()-> System.out.println("Fizz" ));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        Thread buzzThread = new Thread(() ->{
            try {
                fiybas.buzz(()-> System.out.println("Buzz"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread fbThread = new Thread(()->{
            try {
                fiybas.fizzbazz(()-> System.out.println("FizzBuzz" ));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread numberThread = new Thread(()->{
            try {
                fiybas.number(System.out::println);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        fbThread.start();
        numberThread.start();
        fizThread.start();
        buzzThread.start();

    }
}
