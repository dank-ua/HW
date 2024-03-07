package org.example.hw12.fiz;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.function.IntConsumer;

public class Fiybas {
    private  int n;
    private int i = 1;

    public Fiybas(int n) {
        this.n = n;
    }

    public synchronized void fizz(Runnable printFizz) throws InterruptedException{
        while (i<=n){
            if (i%3 == 0 && i%5 !=0){
                printFizz.run();
                i++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException{
        while (i<=n){
            if (i%5 == 0 && i%3 !=0){
                printBuzz.run();
                i++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }
    public synchronized void fizzbazz(Runnable printFizzBuzz) throws InterruptedException{
        while (i<=n){
            if (i%15 == 0){
                printFizzBuzz.run();
                i++;
                notifyAll();
            }else {
                wait();
            }
        }
    }
    public synchronized void number(IntConsumer printNumber) throws InterruptedException{
        while (i <= n){
            if(i%3 != 0 && i%5 != 0){
                printNumber.accept(i);
                i++;
                notifyAll();
            }
            else {
                wait();
            }
        }
    }
}
