package chapter_7.assessment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Question22 {

    private AtomicInteger s1 = new AtomicInteger(0);
    private int s2 = 0;


    public static void main(String[] args) throws InterruptedException {
        Question22 question22 = new Question22();
        question22.countSheep();
    }

    private void countSheep() throws InterruptedException {
        ExecutorService service = null;
        try{
            service = Executors.newSingleThreadExecutor();
            for(int i = 0; i < 100; i++)
                service.execute(() -> {
                    s1.getAndIncrement();
                    s2++;
                });

            Thread.sleep(100);
            System.out.println(s1 + " " + s2);

        }finally {
            if(service != null) service.shutdown();
        }
    }

}
