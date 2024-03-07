package org.example.hw12;

public class Demo {
    public static void main(String[] args) {
        Resource resource= new Resource( 5);
        Producer producer = new Producer(resource);
        Consumer consumer= new Consumer(resource);
        Testfizz testfizz = new Testfizz(resource);

        producer.start();
        consumer.start();
        testfizz.start();
    }
}
