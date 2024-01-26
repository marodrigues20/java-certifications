package chapter_5.reviewed_questions.question_18;

import java.io.Closeable;
import java.io.IOException;


/**
 * 18. What is the output of the following code?
 * CORRECT IS C ( prints out T W D E F)
 *
 * After both resources are declared and created in the try-with-resources statement, T is printed as part of the body.
 * Then the try-with-resources completes and closes the resources in reverse order from which they were declared.
 * After W is printed, an exception is thrown.
 * However, the remaining resource still needs to be closed, so D is printed.
 * Once all the resources are closed, the exception is thrown and swallowed in the catch block, causing E to be printed.
 * Last, the finally block is run, printing F.
 * Therefore, the answer is TWDEF.
 *
 */
public class FamilyCar {

    static class Door implements AutoCloseable{
        @Override
        public void close()  {
            System.out.println("D");
        }
    }

    static class Window implements Closeable{
        @Override
        public void close()  {
            System.out.println("W");
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        var d = new Door();
        try (d; var w = new Window()){
            System.out.println("T");
        } catch (Exception e){
            System.out.println("E");
        }finally {
            System.out.println("F");
        }
    }
}
