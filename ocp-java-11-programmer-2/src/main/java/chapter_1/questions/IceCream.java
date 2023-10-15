package chapter_1.questions;


/**
 * Question 7
 * Letter F correct answer.
 * When using an enum in a switch statement, the case statement must be made up of the enum values only. If the enum
 * name  is used in the case statement value, then the code does not compile. For example, VANILLA is acceptable but
 * Flavors.VANILLA is not. For this reason, the three case statements do not compile, making option F the correct
 * answer. If these three lines were corrected, then the code would compile and produce a NullPointerException at
 * runtime.
 */
public class IceCream {
    enum Flavors{
        CHOCOLATE, STRAWBERRY, VANILLA
    }

    public static void main(String[] args) {
        Flavors STRAWBERRY = null;
        // In order to compile I replaced Flavors.VANILLA to VANILLA and so on.
        switch (STRAWBERRY){
            case VANILLA: System.out.println("v");
            case CHOCOLATE: System.out.println("c");
            case STRAWBERRY: System.out.println("s");
            break;
            default: System.out.println("missing flavors");
        }
    }
}
