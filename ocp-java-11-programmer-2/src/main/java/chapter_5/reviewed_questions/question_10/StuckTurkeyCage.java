package chapter_5.reviewed_questions.question_10;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * 10. Which of the following changes when made independently would make this code compile?
 * (Choose all that apply.)
 *
 * Correct answer is B and C
 *
 * The code does not compile, so option E is incorrect.
 * Option A is incorrect because removing the exception from the declaration causes a compilation error on line 4,
 * as FileNotFoundException is a checked expection that must be handled or declared.
 * Option B is correct because the unhandled exception within the main() method becomes declared.
 * Option C is also correct becuase the exception becomes handled.
 * Option D is incorrect because the exception remain unhandled.
 * Finally, option F is incorrect because the changes for option B or C will allow the code to compile.
 *
 */
public class StuckTurkeyCage implements AutoCloseable{
    @Override
    public void close() throws IOException {
        throw new FileNotFoundException("Cage not closed");
    }

    public static void main(String[] args) {
        /*try (StuckTurkeyCage t = new StuckTurkeyCage()){
            System.out.println("put turkeys in");
        }*/
    }
}
