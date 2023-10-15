package chapter_1.questions;


/**
 * Question 2
 * Letter C is correct answer.
 * When an enum contains only a list of values, the semicolon (;) after the list is optional.
 * When an enum contains any other members, such as a constructor or variable, then it is required. Since the coding is
 * missing the semicolon, it does not compile, making option C the correct answer. There are no other compilation errors
 * in this code. If the mission semicolon was added, then the program would print 0 1 2 at runtime.
 */
public class FlavoursEnum {
    enum Flavors{

        // In the question, the semicolon is being missing. I just added to compile the code.
        VANILLA, CHOCOLATE, STRAWBERRY;
        static final Flavors DEFAULT = STRAWBERRY;

        public static void main(String[] args) {
            for(final var e: Flavors.values())
                System.out.println(e.ordinal() + "");
        }
    }
}
