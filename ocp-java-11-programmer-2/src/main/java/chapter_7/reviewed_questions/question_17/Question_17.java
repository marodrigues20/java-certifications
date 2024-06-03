package chapter_7.reviewed_questions.question_17;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

public class Question_17 {
    public void addAndPrintItems(BlockingDeque<Integer> queue) throws InterruptedException {
            queue.offer(103);
            queue.offer(20, 1, TimeUnit.SECONDS);
            queue.offer(85, 8, TimeUnit.HOURS);
            System.out.print(queue.poll(200, TimeUnit.NANOSECONDS));
            System.out.print(" " + queue.poll(1, TimeUnit.MINUTES));
         }
}
