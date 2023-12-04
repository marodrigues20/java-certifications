package chapter_1.review_questions;

/**
 * Question 10
 * Correct answer is letter E.
 * The code OstrichWrangler class is a static nested class; therefore, it cannot access the instance member count.
 * For this reason, line 14 does not compile, and option E is correct. If the static modifier on line 16 was removed,
 * then the class would compile and produce two files Ostrich.class and Ostrich$OstrichWrangler.class
 */
public class Ostrich {
    private int count;
    private interface Wild{

    }

    /**
     * static nested class
     */

    // static class OstrichWrangler implements Wild{ // correct example code. Just removed to compile the class.
    class OstrichWrangler implements Wild{
        public int stampede(){
            return count;
        }
    }
}
