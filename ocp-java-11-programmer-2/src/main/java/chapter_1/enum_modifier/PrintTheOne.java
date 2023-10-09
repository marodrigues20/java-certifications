package chapter_1.enum_modifier;


/**
 * This class prints the following
 * begin, constructing,end
 * If the OnlyOne enum was used earlier, and therefore initialized sooner, then the line that declares the firstCall
 * variable would not print anything.
 */
public class PrintTheOne {
    public static void main(String[] args) {
        System.out.println("begin,");
        OnlyOne firstCall = OnlyOne.ONCE; //prints construction,
        OnlyOne secondCall = OnlyOne.ONCE; //doesn't print anything
        System.out.println("\nend");
    }
}

