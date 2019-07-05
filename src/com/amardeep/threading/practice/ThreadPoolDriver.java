package com.amardeep.threading.practice;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolDriver {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(4);
        for (int i = 0; i < 20; i++) {
            int count = i;
            try {
                pool.submit(() -> {
                    System.out.println("Printed " + count + " from  " + Thread.currentThread().getName());
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pool.shutDown();
    }
}

class ThreadPool {

    private final int corePoolSize;
    private final BlockingQueue<Runnable> taskQueue;
    private final AtomicInteger workerCount = new AtomicInteger(0);
    private final WorkerFactory factory = new WorkerFactory();
    private volatile boolean isShutdown = false;
    Set<Worker> workers = new HashSet<>();
    private Object lock = new Object();

    public ThreadPool(int nThreads) {
        this.corePoolSize = nThreads;
        taskQueue = new ArrayBlockingQueue<>(64);
    }

    public void submit(Runnable task) throws InterruptedException {
        synchronized (lock) {
            if (workerCount.get() < corePoolSize) {
                Worker worker = factory.newThread(task);
                worker.start();
                workers.add(worker);
                workerCount.incrementAndGet();
            } else {
                taskQueue.put(task);
            }
        }
    }

    public void invokeAll(Collection<Runnable> tasks) {

    }

    public void shutDown() {
        if (taskQueue.remainingCapacity() == 0) {
            synchronized (lock) {
               while(!workers.isEmpty()){
                   for(Iterator<Worker> itr = workers.iterator(); itr.hasNext(); ){
                       Worker worker = itr.next();
                       if(!worker.isActive.get()){
                           worker.interrupt();
                           itr.remove();
                       }
                   }
               }
               isShutdown = true;
            }
        }
    }

    public boolean isShutDown() {
        return isShutdown;
    }

    private final class WorkerFactory implements ThreadFactory {
        AtomicInteger thread = new AtomicInteger(0);

        @Override
        public Worker newThread(Runnable r) {
            return new Worker(r, "pool - " + " thread - " + thread.incrementAndGet());
        }
    }

    private final class Worker extends Thread {
        private Runnable initialTask;
        private AtomicBoolean isActive = new AtomicBoolean(false);

        Worker(Runnable initialTask, String name) {
            super(name);
            this.initialTask = initialTask;
        }

        @Override
        public void run() {
            while (!isShutdown) {
                if (initialTask != null) {
                    Runnable task = initialTask;
                    initialTask = null;
                    isActive.set(true);
                    task.run();
                    isActive.set(false);
                } else {
                    try {
                        Runnable task = taskQueue.take();
                        isActive.set(true);
                        task.run();
                        isActive.set(false);
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread().getName() + "Killed ");
                    }
                }
            }
        }
    }
}