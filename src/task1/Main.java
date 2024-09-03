package task1;

import task1.mq.FileProcessingSystem;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int size = 3000;
        int[] array = generateArray(size);
        int[] array2 = Arrays.copyOf(array, size);
        int[] array3 = Arrays.copyOf(array, size);

        SequentialSum.test(array);
        MultithreadedSum.test(array2);
        ForkJoinSum.test(array3);


        FutureSquareCalculator.calc();

        FileProcessingSystem.run();

    }


    private static int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }
}