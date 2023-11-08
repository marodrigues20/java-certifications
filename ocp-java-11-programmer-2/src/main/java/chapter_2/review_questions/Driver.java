package chapter_2.review_questions;

import jdk.jfr.MemoryAddress;

import java.lang.annotation.Documented;

/**
 * Question 6
 * E, G. The annotation declaration includes one required element, making option A incorrect. Options B, C, and D are
 * incorrect because the Driver declaration does not contain an element named value(). If directions() where renamed in
 * Driver to value(), then options B and D would be correct. The correct answers are options E and G. Option E uses the
 * shorthand form in which the array braces ({}) can be dropped if there is only one element. Option C and F are not
 * valid annotation uses, regardless of the annotation declaration. In this question, the @Documented and @Deprecated
 * annotations have no impact on the solution.
 */
@Documented @Deprecated
public @interface Driver {
    int[] directions();
    String name() default "";
}

//@Driver(directions = {5, 6})
@Driver(directions = 5)
class Taxi{}
