package com.amardeep.threading.batch;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class BatchProcess {
    private static final int NO_OF_THREADS = Runtime.getRuntime().availableProcessors();
    private final ExecutorService pool = new ThreadPoolExecutor(NO_OF_THREADS,
                                                                NO_OF_THREADS,
                                                     0L,
                                                                TimeUnit.SECONDS,
                                                                new ArrayBlockingQueue<>(64),
                                                                new ThreadPoolExecutor.CallerRunsPolicy());

    public List<Future<ServiceResponse>> invokeAllTasks(Collection<Callable<ServiceResponse>> tasks) {
        System.out.println("Tasks submitted before  with threads "+ NO_OF_THREADS + tasks);
        List<Future<ServiceResponse>> results = Collections.EMPTY_LIST;
        try {
            results = this.pool.invokeAll(tasks, 120, TimeUnit.SECONDS);
            System.out.println("Tasks submitted after ");
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public void shutDownService(){
        try{
            this.pool.shutdown();
            this.pool.awaitTermination(120, TimeUnit.SECONDS);
        }catch(InterruptedException ie){
            ie.printStackTrace();;
        }catch(Exception e){
            e.printStackTrace();;
        }finally {
            if(!this.pool.isShutdown()){
                this.pool.shutdownNow();
            }
        }
    }

}
