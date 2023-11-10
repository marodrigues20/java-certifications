package chapter_2.review_questions;


@interface Strong{
    int force();
}

/**
 * Question 18
 * C, D, E, F.
 * Line 22, 23 do not compile because Boolean and void are not supported annotation element types. It must be a primitive,
 * String, Class, enum, another annotation, or an array of these types.
 * Line 24 does not compile because annotation elements are implicitly public.
 * Finally, line 25 does not compile because the Strong annotation does not contain a value() element, so the shorthand
 * notation cannot be used. If line 5 were changed from force() to value(), then line 25 would compile. Without the
 * change, though, the compiler error is one line 25. The rest of the lines do not contain any compilation errors,
 * making options C, D, E, and F correct.
 */
public @interface Wind {

    public static final int temperature = 20;
    //Boolean storm() default true;
    //public void kiteFlying();
    //protected String gusts();
    //Strong power() default @Strong(10);
}
