package com.amardeep.threading.practice;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinPrinting {
    /*
            4 Threads
            1 - Thread1
            2 - Thread2
            3 - Thread3
            4 - Thread4
            5 - Thread1
            ...
     */
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();
        int nThreads = 2;
        BlockingQueue<String> threadQueue = new ArrayBlockingQueue<>(nThreads);
        printerThreaded(counter, threadQueue, nThreads);

    }

    public static void printerThreaded(final AtomicInteger source, final BlockingQueue<String> threadQueue, final int nThreads) {
        ExecutorService pool = Executors.newFixedThreadPool(nThreads);
        for (int i = 0; i < 20; i++) {
            pool.submit(() -> {
                String currentThreadName = Thread.currentThread().getName();
                try {
                    synchronized (source) {
                        while (threadQueue.contains(currentThreadName)) {
                            source.wait();
                        }
                        System.out.println(source.incrementAndGet() + "Printed from " + currentThreadName);
                        threadQueue.put(currentThreadName);
                        if(threadQueue.remainingCapacity() == 0) {
                            threadQueue.take();
                        }
                        source.notifyAll();
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            });
        }
        pool.shutdown();
    }
}

