package chapter_2.review_questions.question_20;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE_USE)
public @interface Floats {
    int buoyancy() default 2;
}
