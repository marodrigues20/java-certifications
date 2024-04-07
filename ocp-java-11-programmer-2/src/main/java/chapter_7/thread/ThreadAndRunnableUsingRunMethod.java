package chapter_7.thread;

import chapter_7.runnable.PrintData;

public class ThreadAndRunnableUsingRunMethod {

    public static void main(String[] args) {
        System.out.println("begin");
        (new ReadInventoryThread()).run();
        (new Thread(new PrintData())).run();
        (new ReadInventoryThread()).run();
        System.out.println("end");
    }
}
