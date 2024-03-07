package org.example.hw12;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

public class Resource {
    private Queue<Integer> sharResource= new LinkedList<>();
    private int capacity;




    public Resource(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int item) throws InterruptedException{
        while (sharResource.size()==capacity){
            wait();
        }
        sharResource.add(item);
        //System.out.println("prod = " + item);
        notify();
    }

    public synchronized int consumer( ) throws InterruptedException{
        while (sharResource.isEmpty()){
            wait();
        }
        Integer item = sharResource.remove();
        if (item % 2 == 0){
            System.out.print(item + " item dil na 2 "  );
        } else {
            System.out.print(  item);
        }
       // System.out.println("consum = " + item);
        notify();
        return item;
    }

    public synchronized int trzz() throws InterruptedException{
        while (sharResource.isEmpty()){
            wait();
        }
        Integer item = sharResource.remove();
        if (item % 3 == 0){
            System.out.print(item + " item dil na 3 "  );
        }else {
            System.out.print(  item);
        }
        notify();
        return item;
    }
}
