package task1.mq;

import java.util.concurrent.*;

public class FileProcessingSystem {
    public static void run() {
        BlockingQueue<File> queue = new ArrayBlockingQueue<>(5);


        Thread generatorThread = new Thread(new FileGenerator(queue));
        generatorThread.start();

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(new FileProcessor(queue, "XML"));
        executor.submit(new FileProcessor(queue, "JSON"));
        executor.submit(new FileProcessor(queue, "XLS"));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        generatorThread.interrupt();
        executor.shutdownNow();
    }
}