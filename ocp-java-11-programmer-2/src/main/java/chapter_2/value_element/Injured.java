package chapter_2.value_element;


/**
 * This annotation is composed of multiple optional elements. In this example, we gave value() a default value, but we
 * could have also made it required.
 */
public @interface Injured {
    String veterinarian() default "unassigned";
    String value() default "foot";
    int age() default 1;
}
