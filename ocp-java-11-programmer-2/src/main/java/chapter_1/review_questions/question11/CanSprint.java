package chapter_1.review_questions.question11;


/**
 * Question 11
 * Letter D is the correct answer.
 * In this example, CanWalk and CanRun both implement a default walk() method.
 * The definition of CanSprint extends these two interfaces and therefore won't compile unless the interface overrides
 * both inherited methods. The version of walk() on line 14 is an overload, not an override, since it take an int value.
 * Since the interface does't override the methods, the compiler can't decide which default method to use, leading to a
 * compiler error and making option D the correct answer.
 */
public interface CanSprint extends CanWalk, CanRun{

    void sprint();

    /**
     * This method is being overloaded. For this reason we have compiler issue on line 13.
     * @param speed
     */
    /*default void walk(int speed){
        System.out.println("Sprinting");
    }*/

    /**
     * This method is being overrides.
     */
    default void walk(){
        System.out.println("Sprinting");
    }
    private void testWalk(){}



}
