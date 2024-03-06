package org.example.hw12;

import java.util.concurrent.TimeUnit;

public class Thread1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            try {


                while (true) {

                    long currenTime = System.currentTimeMillis() - startTime;
                    System.out.println("startTime = " + (currenTime / 1000));

                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
//
        Thread thread2 = new Thread(() ->{
            try {
                while (true) {
                    //long fiveSecVait = System.currentTimeMillis() - startTime;

                    Thread.sleep(5000L);
                    System.out.println("Минуло 5 секунд" );
                }
            }catch (InterruptedException f){
                throw new RuntimeException(f);
            }

        });

        thread1.start();
        thread2.start();

    }
}
