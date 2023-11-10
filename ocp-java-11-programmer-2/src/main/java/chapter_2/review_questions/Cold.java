package chapter_2.review_questions;


import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

enum UnitOfTemp {C, F}

@interface Snow{
    boolean value();
}


/**
 * Question 12
 * B, C, D. Annotations cannot have constructors, so line 16 does not compile.
 * Annotations can have variables, but they must be declared with a constant value. For this reason, line 19 does not compile.
 * Line 21 does not compile as the element unit is missing parentheses after the element name.
 * Line 23 compiles and shows how to use annotation type with a default value.
 */
@Target(ElementType.METHOD)
public @interface Cold {
    //private Cold(){}
    //int temperature;
    //UnitOfTemp unit default UnitOfTemp.C;
    Snow snow() default @Snow(true);
}
