package chapter_2.stage_04;


/**
 * The elements cuteness() and softness() are both considered abstract and public, even though only one of them is
 * marked as such.
 */
public @interface Fluffy {

    int cuteness();
    public abstract int softness() default 11;

    //protected  Material material();  // Does not compile because the access modifier conflicts with the elements being
                                       // implicitly public.

    //private String friendly();      // Does not compile because the access modifier conflicts with the elements being
                                      // implicitly public.

    //final boolean isBunny();      // Does not compile because, like abstract methods, it cannot be marked final.
}
