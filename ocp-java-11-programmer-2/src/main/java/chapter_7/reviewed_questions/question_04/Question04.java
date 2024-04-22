package chapter_7.reviewed_questions.question_04;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Question04 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = //w1
                Executors.newSingleThreadScheduledExecutor();
        /*service.scheduleWithFixedDelay(() -> {
            System.out.println("Open Zoo");
            return null; // w2
        }, 0, 1, TimeUnit.MINUTES);*/
        var result = service.submit(() ->  // w3
                System.out.println("Wake Staff"));
        System.out.println(result.get()); // w4
    }
}
