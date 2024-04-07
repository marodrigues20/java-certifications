package chapter_7.pooling;

public class CheckResults_v2 {

    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            for (int i = 0; i < 500; i++)
                CheckResults_v2.counter++;
        }
        ).start();
        while (CheckResults_v2.counter < 100) {
            System.out.println("Not reached yet");
            Thread.sleep(1000); // 1 SECOND
        }
        System.out.println("Reached");
    }
}
