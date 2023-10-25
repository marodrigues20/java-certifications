package chapter_2.creating_annotation.stage_02;


/**
 * The default value of an annotation cannot be just any value. Similar to case statement values, the default value of
 * an annotation must be a non-null constant expression
 */
public @interface BadAnnotation {
    //String name() default new String("");  // DOES NOT COMPILE
    String address() default "";

    //String title() default null; // DOES NOT COMPILE
}
