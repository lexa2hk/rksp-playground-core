package task1;

import java.util.concurrent.*;
import java.util.Random;

public class MultithreadedSum {
    public static void test(int[] array) throws InterruptedException, ExecutionException {
        int numThreads = 200;
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        long startTime = System.currentTimeMillis();

        Future[] futures = new Future[numThreads];
        int chunkSize = array.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            final int start = i * chunkSize;
            final int end = (i == numThreads - 1) ? array.length : start + chunkSize;
            futures[i] = executor.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    Thread.sleep(1);
                    sum += array[j];
                }
                return sum;
            });
        }

        long sum = 0;
        for (Future<Long> future : futures) {
            sum += future.get();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Multithreaded sum: " + sum);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

}
