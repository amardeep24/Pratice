package com.amardeep.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializationDriver {
    public static void main(String[] args) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            SerializedObject obj = new SerializedObject(10, new ArrayList<>(Arrays.asList(10, 20)), new StringBuffer("abc"));
            oos.writeObject(obj);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);

            SerializedObject obj2 = (SerializedObject)ois.readObject();

            //Testing
            System.out.println(obj == obj2); //false
            System.out.println("Initial value of List obj" + obj.getList());
            System.out.println("Initial value of List obj2" + obj2.getList());

            obj.getList().add(30);
            System.out.println("Modified value of List obj" + obj.getList());
            System.out.println("Modified value of List obj2" + obj2.getList());

            obj2.getList().add(40);
            System.out.println("Modified value of List obj" + obj.getList());
            System.out.println("Modified value of List obj2" + obj2.getList());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class SerializedObject implements Serializable {
    private final int value;
    private final List<Integer> list;
    private final StringBuffer buffer;

    SerializedObject(int value, List<Integer> list, StringBuffer buffer){
        this.list = list;
        this.value = value;
        this.buffer = buffer;
    }


    public int getValue() {
        return value;
    }

    public List<Integer> getList() {
        return list;
    }


    public StringBuffer getBuffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return "SerializedObject{" +
                "value=" + value +
                ", list=" + list +
                '}';
    }
}
