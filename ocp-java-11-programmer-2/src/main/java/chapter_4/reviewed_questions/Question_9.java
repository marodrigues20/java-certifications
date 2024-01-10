package chapter_4.reviewed_questions;


import java.util.stream.LongStream;

/**
 * Which of the following can we add after line 6 for the code to run without error and not produce any output?
 * (Choose all that apply)
 * Answer B and D.
 *
 * Lines
 */
public class Question_9 {

    public static void main(String[] args) {
        var stream = LongStream.of(1, 2, 3);
        var opt = stream.map(n -> n * 10)
                .filter(n -> n < 5).findFirst();

        if(opt.isPresent())
            System.out.println(opt.getAsLong());

        opt.ifPresent(System.out::println);
    }

}
