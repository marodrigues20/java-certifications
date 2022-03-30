package chapter_7.assessment;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Question17 {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>();
        Question17 question17 = new Question17();
        question17.addAndPrintItems(blockingQueue);
    }

    public void addAndPrintItems(BlockingQueue<Integer> queue) throws InterruptedException {
        queue.offer(103);
        queue.offer(20, 1, TimeUnit.SECONDS);
        queue.offer(85, 7, TimeUnit.HOURS);
        System.out.print(queue.poll(200, TimeUnit.NANOSECONDS));
        System.out.println(" " + queue.poll(1, TimeUnit.MINUTES));

    }
}
