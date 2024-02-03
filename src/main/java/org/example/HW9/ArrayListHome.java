package org.example.HW9;

public class ArrayListHome {
    private Object[] data;
    private int size;
    private static final int ARR = 10;

    public ArrayListHome() {
        this.data = new Object[ARR];
        size = 0;
    }

    public void add(Object input) {
        if (size >= data.length) {
            Object[] newData = new Object[data.length * 2];
            System.arraycopy(data, 0, newData, 0, size);
            data = newData;
            data[size++] = input;
        }

    }

    public Object get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();

        }
        return data[size];

    }
    public void remove(int index){
        if (index<0 || index >size){
            throw new ArrayIndexOutOfBoundsException();
        }
        for (int i = index; i< size -1 ; i ++){
            data[i] = data[size +1];
        }
        data[--size]=  null;

    }

    public void clear(){
        data = new Object[ARR];
        size = 0;
    }
    public int size(){
        return  size;
    }




}
