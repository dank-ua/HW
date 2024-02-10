package org.example.hw9;


public class MyQueue <A>{
    private Dode<A> head;
    private Dode<A> tail;
    private int size;
    public void  add(A value){
        Dode<A> dode = new Dode<>( value);

        if (head == null){
            head = dode;
            tail = dode;
        }else {
            tail.next = dode;
            dode.previus = tail;
            tail = dode;
        }
        size ++;
    }
    public void  clear(){
        head = null;
        tail = null;
        size = 0;
    }
    public int size() {

        return size;
    }
    public A peek(){
        if (head == null){
            return null;
        }
        return head.value;

    }
    public A poll(){
        if (head == null){
            return  null;
        }
        A rem = head.value;
        head = head.next;
        if(head == null){
            tail = null;
        }else {
            head.previus = null;
        }
        size --;
        return rem;
    }

    private static class Dode<A>{
        A value;
        Dode<A> next;
       Dode<A> previus;

        public Dode(A value) {
            this.value = value;
        }
    }
}
