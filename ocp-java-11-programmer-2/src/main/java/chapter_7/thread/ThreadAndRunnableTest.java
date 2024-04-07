package chapter_7.thread;

import chapter_7.runnable.PrintData;

public class ThreadAndRunnableTest {

    public static void main(String[] args) {
        System.out.println("begin");
        (new ReadInventoryThread()).start();
        (new Thread(new PrintData())).start();
        (new ReadInventoryThread()).start();
        System.out.println("end");
    }
}
