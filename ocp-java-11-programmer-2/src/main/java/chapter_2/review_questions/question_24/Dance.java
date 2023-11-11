package chapter_2.review_questions.question_24;

public @interface Dance {
    long rhythm() default 66;
    int[] value();
    String track() default "";
    final boolean fast = true;
}
