package chapter_1.questions;

/**
 * Question 13
 * Letters E and G are correct.
 * For this question, it helps to remember which implicit modifiers the compiler will insert and which it will not.
 * Line 12 and 13 compile with interface variables assumed to be public, static, and final. Line 14 also compiles, as
 * static methods are assumed to be public if not otherwise marked. Line 15 does not compile. Non-static methods within
 * an interface must be explicitly marked private or default. Line 16 compiles, with the public modifiers being added
 * by the compiler. Line 25 does not compile, as interface do not have protected members. Finally, line 18 compiles,
 * with no modifiers being added by the compiler
 */
public interface Herbivore {

    int amount = 10;
    static boolean gather = true;
    static void eatGrass(){}
    /*int findMore(){
        return 2;
    }*/
    default float rest(){
        return 2;
    }

    /*protected int chew(){
        return 13;
    }*/
    private static void eatLeaves(){}
}
