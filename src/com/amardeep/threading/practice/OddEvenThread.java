package com.amardeep.threading.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class OddEvenThread {
    public static void main(String[] args) {
       // OddEvenOneLoop.printOddEvenTwoThreads();
        ProducerConsumer.start();
    }
}
class OddEvenTwoLoops{

    public static void printOddEvenTwoThreads(){
        Object lock = new Object();
        new Thread(() ->{
            for(int i = 1; i<=100; i++){
                synchronized (lock) {
                    try {
                        if (i % 2 != 0) {
                            System.out.println("From odd thread " + i);
                            lock.notify();
                            lock.wait();
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(() ->{
            for(int i = 1; i<=100; i++){
                synchronized (lock) {
                    try {
                        if (i % 2 == 0) {
                            System.out.println("From even thread " + i);
                            lock.notify();
                            if(i != 100) {
                                lock.wait();
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
class OddEvenOneLoop{

    public static void printOddEvenTwoThreads(){
        AtomicInteger counter = new AtomicInteger(0);
        Runnable logic = () ->{
            int len = 100;
            String threadName = Thread.currentThread().getName();
            while(counter.get() < len){
                synchronized (counter) {
                    try {
                        if (counter.get() % 2 == 0){
                            System.out.println(" From thread " + threadName + counter.incrementAndGet()); //odd
                            counter.notify();
                            if(counter.get() < len) {
                                counter.wait();
                            }
                        }
                        else{
                            System.out.println(" From thread " + threadName + counter.incrementAndGet()); //even
                            counter.notify();
                            if(counter.get() < len) {
                                counter.wait();
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(logic, "Odd").start();
        new Thread(logic, "Even").start();
    }
}
class ProducerConsumer{
    public static void start(){
        Queue<Integer> queue = new LinkedList<>();
        Runnable producer = () ->{
            for(int i=1; i<=100; i++){
                synchronized (queue) {
                    try {
                        System.out.println("Producer produced "+ i);
                        queue.offer(i);
                        queue.notify();
                        if(i!=100) {
                            queue.wait();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Runnable consumer = () ->{
                while(true){
                    synchronized (queue) {
                        try {
                            Integer consumed = queue.poll();
                            System.out.println("Consumer consumed "+ consumed);
                            queue.notify();
                            if(consumed!= null && consumed == 100){
                                break;
                            }else{
                                queue.wait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        };
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}