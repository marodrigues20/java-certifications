package chapter_1.review_questions;


/**
 * Question 9
 * Letter correct is G.
 * The code does not compile! The main() method uses an anonymous inner class that inherits from Spirit, which is not
 * allowed. If Spirit was not marked final, then option C and F would be correct. Option A would print Booo!!!, while
 * options B, D, and E would not compile for various reasons.
 */
public class Ghost {

    public static void boo(){
        System.out.println("Not scared");
    }

    protected final class Spirit{
        public void boo(){
            System.out.println("Booo!!!");
        }
    }

    public static void main(String... haunt){

        // I am declaring one anonymous class. However, The inner class Spirit was marked as final and can't be
        //inherit from Spirit.
        //var g = new Ghost().new Spirit() {};

    }
}
