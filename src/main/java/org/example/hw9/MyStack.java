package org.example.hw9;

import java.util.Objects;

public class MyStack <S>{


        private Bode<S> head;
        private Bode<S> tail;
        private int size;
        public void push(S value){
            Bode<S> bode = new Bode<>(value);
            if (head == null){
                head = bode;
                tail = bode;
            }else {
                tail.next = bode;
                bode.previus = tail;
                tail = bode;
            }
size ++;
        }
        public S remove(int index){
            Objects.checkIndex(index, size);
            Bode<S> cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            if (cur.previus != null) {
                cur.previus.next = cur.next;
            }else {
                head = cur.next;
            }if (cur.next != null){
                cur.next.previus = cur.previus;
            }else {
                tail = cur.previus;
            }
            S re = cur.value;
            cur.value = null;
            cur.next = null;
            cur.previus = null;
            size--;
            return  re;

            // спитат як програма знає що некст це наступний а превіос попередній елемент
            // і аналогічно із хвостом і головою, це так в джаві прописано

        }
        public void clear(){
            head = null;
            tail = null;
            size = 0;
        }
    public int size(){
            return  size;
    }
    public  S peek(){

            if (head == null){
                return null;
            }
            return head.value;
    }
    public S pop(){

            if (head == null){
                return  null;
            }
            S rer = head.value;
                head = head.next;
                size--;
                return  rer;

    }
    private static class Bode<S>{
        S value;
        Bode<S> next;
        Bode<S>  previus;

        public Bode(S value){
            this.value = value;
        }
    }
}
