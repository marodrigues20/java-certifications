package chapter_1.review_questions;

/**
 * Question 15.
 * Letter G is the correct answer.
 * The isHealthy() method is marked abstract in the enum; therefore, it must be implemented in each enum value
 * declaration. Since only INSECTS implements it, the code does not compile, making option G correct. If the code were
 * fixed to implement the isHealthy() method in each enum value, then the first three values printed would be INSECTS,
 * 1, and true, with the fourth being determined by the implementation of COOKIES.isHealthy().
 */
public class Bear {

    // Parts commented to compile the code. To check the example uncomment the code.
    enum FOOD {
        //BARRIERS,
        INSECTS {
            public boolean isHealthy(){
                return true;
            }};
            //,
            //FISH, ROOTS, COOKIES, HONEY;

        public abstract boolean isHealthy();

    }

        public static void main(String[] args){
            System.out.println(FOOD.INSECTS);
            System.out.println(FOOD.INSECTS.ordinal());
            System.out.println(FOOD.INSECTS.isHealthy());
            //System.out.println(FOOD.COOKIES.isHealthy());
    }
}
