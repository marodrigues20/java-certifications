package chapter_2.review_questions.quesiton_23;

import java.lang.reflect.Field;

/**
 * Question 23
 * Correct is A.
 *
 * This question, like some questions on the exam, includes extraneous information that you do not need to know to
 * solve it. Therefore, you can assume the reflection code is valid. That said, this code is not without problems. The
 * default retention policy for all annotations is RetentionPolicy.CLASS if not explicitly stated otherwise. This means
 * the annotation information is discarded at compile time and not available at runtime. For this reason, none of the
 * members will print anything, making option A correct.
 *
 * If @Retention(RetentionPolicy.RUNTIME) were added to the declaration of Plumber, then the worker member would cause
 * the default annotation value(), Mario, to be printed at runtime, an option B would be the correct answer. Note that
 * foreman would not cause Mario to be printed even with the corrected retention annotation. Setting the value of the
 * annotation is not the same as setting the value of the variable foreman.
 */
public class Team {
    @Plumber("") private String foreman = "Mario";
    @Plumber private String worker = "Kelly";
    @Plumber("Kelly") private String trainee;

    public static void main(String[] args) {
        var t = new Team();
        var fields = t.getClass().getDeclaredFields();
        for(Field field : fields)
            if(field.isAnnotationPresent(Plumber.class))
                System.out.println(field.getAnnotation(Plumber.class).value());
    }
}
