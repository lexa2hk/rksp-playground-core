package task1;

import java.util.Scanner;
import java.util.concurrent.*;


public class FutureSquareCalculator {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    public Future<Integer> calculateSquare(int number) {
        return executor.submit(() -> {
            int delay = (int) (Math.random() * 5 + 1);
            TimeUnit.SECONDS.sleep(delay);
            return number * number;
        });
    }

    public static void calc() {
        FutureSquareCalculator calculator = new FutureSquareCalculator();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число (q exit): ");
        while (true) {

            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                calculator.executor.shutdown();
                break;
            }

            try {
                int number = Integer.parseInt(input);
                Future<Integer> futureResult = calculator.calculateSquare(number);

                int result = futureResult.get();
                System.out.println("Результат: " + result);

            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}