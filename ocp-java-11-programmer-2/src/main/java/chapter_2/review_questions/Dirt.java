package chapter_2.review_questions;

import java.lang.annotation.Documented;

enum Color {GREY, BROWN}

/**
 * Question 2:
 * D,F. Line 14 does not compile because = is used to assign a default value, rather than the default modifier. Line 18
 * does not compile because annotation and interface constants are implicitly public and cannot be marked private.
 */
@Documented
public @interface Dirt {

    boolean wet();
    //String type() = "unknown";
    public Color color();

    //private static final int slippery = 5;
}
