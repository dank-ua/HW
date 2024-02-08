package org.example.hw9;

import org.w3c.dom.Node;

import java.util.Objects;

public class MyLinkedList <T>{
    private Node<T> head;
    private Node<T> tail;
    private int size;
    public void add(T value){
        Node<T> node = new Node<>(value);

        if (head == null){
            head = node;
            tail = node;
        }else {
            tail.next = node;
            node.previus = tail;
            tail = node;
        }
        size ++;

    }
    public T remove(int index){
        Objects.checkIndex(index, size);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current= current.next ;
        }

       if (current.previus != null){
           current.previus.next = current.next;
       }else {
           head = current.next;
       }if (current.next != null){
           current.next.previus = current.previus;
        }else {
           tail = current.previus;
        }
        T re = current.value;
        current.value = null;
        current.next = null;
        current.previus = null;
        size--;
        return re;




    }
    public int size() {
        return size;
    }
    public T get(int index ){
        Objects.checkIndex(index, size);

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current= current.next ;
        }
        return current.value;


    }
    public void  clear(){
        head = null;
        tail = null;
        size = 0;
    }
    private static class Node<T>{
         T value;
         Node<T> next;
         Node<T> previus;

        public Node(T value) {
            this.value = value;
        }
    }

}
