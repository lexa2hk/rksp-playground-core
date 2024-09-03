package task1.mq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

class FileProcessor implements Runnable {
    private final BlockingQueue<File> queue;
    private final String fileType;

    public FileProcessor(BlockingQueue<File> queue, String fileType) {
        this.queue = queue;
        this.fileType = fileType;
    }

    @Override
    public void run() {
        while (true) {
            try {
                File file = queue.take();
                if (file.getType().equals(fileType)) {
                    System.out.println("Processing: " + file);
                    Thread.sleep(file.getSize() * 7L);
                } else {
                    queue.put(file);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    /**
     * Implementation below don't use under hood sleep:
     *
     * public E take() throws InterruptedException {
     *         final ReentrantLock lock = this.lock;
     *         lock.lockInterruptibly();
     *         try {
     *             while (count == 0)
     *                 notEmpty.await();
     *             return dequeue();
     *         } finally {
     *             lock.unlock();
     *         }
     *     }
     */
    public void run2() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                File file = queue.poll(500, TimeUnit.MILLISECONDS);
                if (file == null) {
                    continue;
                }

                if (file.getType().equals(fileType)) {
                    System.out.println("Processing: " + file);
                    Thread.sleep(file.getSize() * 7L);
                } else {
                    queue.put(file);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}