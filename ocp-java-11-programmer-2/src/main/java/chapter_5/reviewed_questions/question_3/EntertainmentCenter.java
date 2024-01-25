package chapter_5.reviewed_questions.question_3;

import java.io.Closeable;
import java.io.IOException;


/**
 * 3. What is the output of the following code?
 * Correct Answer is G.
 *
 * A try-with-resource statement uses parentheses, (), rather than braces, {}, for the try section.
 * This is likely subtler than a question that you'll get on the exam, but it is still important to be on alert for
 * details.
 * If parentheses were used instead of braces, then the code would compile and print TWDF at runtime.
 *
 */
public class EntertainmentCenter {

    static class TV implements AutoCloseable{
        @Override
        public void close() throws Exception {
            System.out.println("D");
        }
    }

    static class MediaStreamer implements Closeable{
        @Override
        public void close() throws IOException {
            System.out.println("W");
        }
    }

    public static void main(String[] args) {
        var w = new MediaStreamer();
        try{
            TV d = new TV(); w;
        }
        {
            System.out.println();
        }catch( Exception e){
            System.out.println("E");
        } finally{
            System.out.println("F");
        }
    }
}
