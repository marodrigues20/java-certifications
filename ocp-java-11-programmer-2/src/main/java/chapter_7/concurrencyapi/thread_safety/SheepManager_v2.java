package chapter_7.concurrencyapi.thread_safety;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SheepManager_v2 {

    private AtomicInteger sheepCount = new AtomicInteger(0);

    private void incrementAndReport(){
        System.out.print((sheepCount.incrementAndGet()) + " ");
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try{
            service = Executors.newFixedThreadPool(20);
            SheepManager_v2 manager = new SheepManager_v2();
            for(int i = 0; i < 10; i++){
                service.submit(() -> manager.incrementAndReport());
            }
        }finally {
            if(service != null) service.shutdown();
        }
    }


}
