package chapter_2.repeatable_annotation;

/**
 * Notice that we never actually use @Risks in our Zoo class. Given the declaration of the
 * Risk and Risks annotations, the compiler takes care of applying the annotation for us.
 */
public class Zoo {

    public static class Monkey{}

    @Risk(danger = "Silly")
    @Risk(danger = "Aggressive", level = 5)
    @Risk(danger = "Violent", level = 10)
    private Monkey monkey;
}
