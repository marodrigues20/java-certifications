package chapter_1.questions;


/**
 * Question 3
 * Letter C is the correct answer.
 * Popcorn is an inner class. Inner classes are only allowed to contain static variables that are marked final. Since
 * there are no other compilation errors, option C is the only correct answer. If the final modifier was added on line
 * 25, then the code would print 10 at runtime. Note that private constructors can be used by any methods within the
 * same class.
 */
public class Movie {
    private int butter = 5;
    private Movie(){}

    /**
     * Popcorn is a inner class
     */
    protected class Popcorn{
        private Popcorn(){}

        /**
         *  I marked the variable as final to compile the code. Original question does not have the final keyword.
         */
        public static final int butter = 10;
        public void startMovie(){
            System.out.println(butter);
        }
    }
    public static void main(String[] args) {
        var movie = new Movie();
        Movie.Popcorn in = new Movie().new Popcorn();
        in.startMovie();
    }
}
