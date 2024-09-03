package task1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum {
    public static void test(int[] array) throws InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        long startTime = System.currentTimeMillis();

        long sum = pool.invoke(new SumTask(array, 0, array.length));

        long endTime = System.currentTimeMillis();
        System.out.println("ForkJoin sum: " + sum);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

    static class SumTask extends RecursiveTask<Long> {
        private static final int THRESHOLD = 1000;
        private final int[] array;
        private final int start;
        private final int end;

        SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sum += array[i];
                }
                return sum;
            } else {
                int middle = (start + end) / 2;
                SumTask leftTask = new SumTask(array, start, middle);
                SumTask rightTask = new SumTask(array, middle, end);
                leftTask.fork();
                return rightTask.compute() + leftTask.join();
            }
        }
    }
}
