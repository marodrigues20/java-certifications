package chapter_5.reviewed_questions.question_25;


/**
 * What is the output of the following program?
 * Correct answer is: G
 *
 * The code does not compile because the resource walk1 is not final or effectively final and cannot be used in the
 * declaration of a try-with-resources statement.
 * If the line that set walk1 to null was removed, then the code would compile and print blizzard 2 at runtime, with
 * the exception inside the try block being the primary exception since it is thrown first.
 * Then two suppressed exceptions would be added to it when trying to close the AutoCloseable resources.
 */
public class SnowStorm {

    static class WalkToSchool implements AutoCloseable {
        @Override
        public void close() throws Exception {
            throw new RuntimeException("flurry");
        }
    }


    public static void main(String[] args) {
        WalkToSchool walk1 = new WalkToSchool();
        try(walk1; WalkToSchool walk2 = new WalkToSchool()){
            throw new RuntimeException("blizzard");
        } catch (Exception e){
            System.out.println(e.getMessage()
            + " " + e.getSuppressed().length);
        }
        //walk1 = null;
    }
}
