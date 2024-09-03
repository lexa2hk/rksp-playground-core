package task1;

import java.util.Random;

public class SequentialSum {
    public static void test(int[] array) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        long sum = calculateSum(array);

        long endTime = System.currentTimeMillis();
        System.out.println("Sequential sum: " + sum);
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
    }

    private static long calculateSum(int[] array) throws InterruptedException {
        long sum = 0;
        for (int num : array) {
            Thread.sleep(1); // Задержка в 1 мс
            sum += num;
        }
        return sum;
    }
}