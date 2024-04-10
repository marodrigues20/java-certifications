package chapter_7.wait_results;

import java.util.concurrent.*;

public class CheckResults_v3 {

    private static int counter = 0;

    public static void main(String[] unused) {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 500; i++) CheckResults_v3.counter++;
            });
            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!");
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.out.println("Not reached in time");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
