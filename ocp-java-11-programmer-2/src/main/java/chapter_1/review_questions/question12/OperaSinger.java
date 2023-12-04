package chapter_1.review_questions.question12;

/**
 * Question 12.
 * Letter C is correct.
 * The functional interface takes two int parameters. The code on line m1 attempts to use them as if one is an Object,
 * resulting in a compiler error and making option C the correct answer. It also tries to return String even though
 * the return data type for the functional interface method is boolean. It is tricky to use types in a lambda when they
 * are implicity specified. Remember to check the interface for the real type.
 */
public class OperaSinger {

    public static void main(String[] args){
        // Commented the real example line to compile the code.
        //check((h, l) -> h.toString(), 5); // m1
    }

    private static void check(Sing sing, int volume){
        if(sing.isTooLoud(volume, 10)) //m2
            System.out.println("not so great");
        else System.out.println("great");
    }
}
