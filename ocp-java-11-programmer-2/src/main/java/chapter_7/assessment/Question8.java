package chapter_7.assessment;

import java.util.stream.LongStream;

public class Question8 {

    private static int counter;

    public static void main(String[] args) {
        counter = 0;
        Runnable task = () -> counter++;
        LongStream.range(1, 500)
                .forEach(m -> new Thread(task).run());

        System.out.println(counter);
    }
}
