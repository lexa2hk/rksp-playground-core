package task1.mq;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

class FileGenerator implements Runnable {
    private final BlockingQueue<File> queue;
    private final Random random = new Random();

    public FileGenerator(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String[] types = {"XML", "JSON", "XLS"};
        while (true) {
            try {
                String type = types[random.nextInt(types.length)];
                int size = 10 + random.nextInt(100);
                File file = new File(type, size);
                queue.put(file);
                System.out.println("Generated: " + file);

                Thread.sleep(100 + random.nextInt(901));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

