package chapter_2.review_questions;

import java.lang.annotation.Inherited;

class Food{}

/**
 * Question 5
 * B, C. Line 17 does not compile because the default value of an element must be a non-null constant expression.
 * Line 18 also does not compile  because an element type must be one of the predefined immutable types: a primitive,
 * String, Class, enum, another annotation, or an array of these types. The rest of the lines do not contain any
 * compilation error.
 */
@Inherited
public @interface Unexpected {

    //public String rsvp() default null;

    //Food food();

    public String[] dessert();

    final int numberOfGuests = 5;

    long startTime() default 0L;
}
