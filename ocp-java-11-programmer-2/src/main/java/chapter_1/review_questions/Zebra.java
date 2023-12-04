package chapter_1.review_questions;


/**
 * Question 25
 * Letter B is the correct answer.
 * Zebra.this.x is the correct way to refer to x in the Zebra class. Line 22 defines an abstract local class within a
 * method, while line 29 defines a concrete anonymous class that extends the Stripes class. The code compiles without
 * issue and prints x is 24 at runtime, making option B the correct answer.
 *
 */
public class Zebra {

    private int x = 24;

    public int hunt(){
        String message = "x is ";

        /**
         * Abstract Local Class
         */
        abstract class Stripes{
            private int x = 0;
            public void print(){
                System.out.println(message + Zebra.this.x);
            }
        }

        var s = new Stripes(){};
        s.print();
        return x;
    }

    public static void main(String[] args) {
        new Zebra().hunt();
    }

}
