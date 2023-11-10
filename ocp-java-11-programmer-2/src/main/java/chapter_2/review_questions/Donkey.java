package chapter_2.review_questions;

import java.util.ArrayList;
import java.util.List;


/**
 * Question 16
 * Correct is F.
 * The @SafeVarargs annotation does not take a value and can be applied only to methods that cannot be overridden
 * (marked private , static , or final). For these reasons, options A and B produce compilation errors. Options C also
 * does not compile, as this annotation can be applied only to other annotations. Even if you didn't remember that, it's
 * clear it has nothing to do with hiding a compiler warning. Option D does not compile as @SuppressWarnings requires a
 * value. Both options E and F allow the code to compile without error, although only option F will cause a compile without
 * warnings. The unchecked value is required when performing unchecked generic operations.
 *
 */
public class Donkey {
    @SuppressWarnings("unchecked")
    public String kick(List... t){
        t[0] = new ArrayList();
        t[0].add(1);
        return (String)t[0].get(0);
    }
}
