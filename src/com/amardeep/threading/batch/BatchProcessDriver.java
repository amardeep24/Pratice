package com.amardeep.threading.batch;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class BatchProcessDriver {
    private static final int BATCH_SIZE = 10;
    public static void main(String[] args) {
        BatchProcess process = new BatchProcess();
        List<Callable<ServiceResponse>> tasks = LongStream.range(0, 300)
                                                          .mapToObj((long i) -> new ServiceResponse("test"+i, i))
                                                          .map(Task::new)
                                                          .collect(Collectors.toList());
        IntStream.range(0, tasks.size() / BATCH_SIZE)
                 .mapToObj(i -> tasks.subList(i*BATCH_SIZE, Math.min(i*BATCH_SIZE + BATCH_SIZE, tasks.size())))
                // .parallel()
                 .flatMap(task -> process.invokeAllTasks(task).stream())
                 .map(future -> {
                     ServiceResponse res = null;
                     try {
                         res = future.get();
                     }catch (InterruptedException ie){
                         ie.printStackTrace();
                     }catch (ExecutionException ee){
                         ee.printStackTrace();
                     }
                     return res;
                 }).forEach(System.out::println);
    }
}
