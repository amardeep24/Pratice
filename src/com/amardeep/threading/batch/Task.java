package com.amardeep.threading.batch;

import java.util.concurrent.Callable;

public class Task implements Callable<ServiceResponse> {
    private ServiceResponse res;
    public Task(ServiceResponse res){
        this.res = res;
    }

    @Override
    public String toString() {
        return "Task{" +
                "res=" + res +
                '}';
    }

    @Override
    public ServiceResponse call() {
        try{
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
        return this.res;
    }
}
