package chapter_7.concurrencyapi.collections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Food {
}

class Water {
}

public class Fox {

    public void eatAndDrink(Food food, Water water) {
        synchronized (food) {
            System.out.println("Got Food");
            moved();
            synchronized (water) {
                System.out.println("Got Water");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized (water) {
            System.out.println("Got Water!");
            moved();
            synchronized (food) {
                System.out.println("Got Food!");
            }
        }
    }

    private void moved() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Handle Exception
        }
    }

    public static void main(String[] args) {
        // Create participants and resources
        Fox foxy = new Fox();
        Fox tails = new Fox();
        Food food = new Food();
        Water water = new Water();
        // Process Data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
