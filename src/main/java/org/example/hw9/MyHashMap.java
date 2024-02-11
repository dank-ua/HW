package org.example.hw9;

import java.util.Arrays;
import java.util.Map;

public class MyHashMap <K, V> {
    private static final int SIZE = 8;
    private final Entry<K, V> [] entries = new Entry[SIZE];
    private int size;

    public void put(K key, V value){
        int index = gIndex(key);
        Entry<K, V> entry = new Entry<>(key, value);
        if (entries[index] == null){
            entries[index] = entry;
            size++;

        }else{
            Entry<K,V> real = entries[index];
            while (real.next != null){
                if (real.key.equals(key)){
                    real.value = value;
                    return;
                }
                real = real.next;
            }
            real.next = entry;
            size++;
        }
    }
    public void remove(K key){
        int index = gIndex(key);
        Entry<K, V> curva = entries[index];
        Entry<K, V> poper = null;
        while (curva != null){
            if (curva.key.equals(key)){
                if (poper == null){
                    entries[index] = curva.next;
                }else {
                    poper.next = curva.next;
                }
                size -- ;
                return;
            }
            poper = curva;
            curva = curva.next;
        }


    }
    public void clear(){
        Arrays.fill(entries, null);
        size = 0;

    }
    public int size(){
        return size;
    }
    public V get(K key){
        int index = gIndex(key);
        Entry<K, V> cur = entries[index];
        while (cur != null){
        if (cur.key.equals(key)){
            return cur.value;
        }
        cur = cur.next;
        }
        return  null;
        }


    private  int gIndex(K key){
        return Math.abs(key.hashCode()% entries.length);
    }
    private static class Entry<K, V>{
        K key;
        V value;
        Entry<K, V> next;
        public Entry(K key, V value){
            this.key=key;
            this.value = value;
        }
    }

}
