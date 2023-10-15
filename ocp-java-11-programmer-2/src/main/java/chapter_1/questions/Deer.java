package chapter_1.questions;


/**
 * Question 14
 * Letter E is correct.
 * Diet is an inner class, which requires an instance of Deer to instantiate. Since the main() method is static,
 * there is no such instance. Therefore, the main() method does not compile, and option E is correct. If a reference to
 * Deer were used, such as calling new Deer().new Diet(), then the code would compile and print bc at runtime.
 */
public class Deer {

    enum Food { APPLES, BERRIES, GRASS }

    /**
     * Inner Class
     */
    protected class Diet {
        private Food getFavorite(){
            return Food.BERRIES;
        }
    }

    public static void main(String[] seasons){
        //switch (new Diet().getFavorite()){ commented to compile class. But this is the real example
        switch (new Deer().new Diet().getFavorite()){
            case APPLES: System.out.println("a");
            case BERRIES: System.out.println("b");
            default: System.out.println("c");
        }
    }
}
