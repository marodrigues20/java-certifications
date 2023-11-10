package chapter_2.review_questions.question_20;

import java.util.function.Predicate;

interface Wood{}
@Floats
class Duck{}

/**
 * Question 20
 * D, F. Line 19 contains a compiler error since the element buoyancy is required in the annotation. If the element
 * were renamed to value() in the annotation declaration, then the element name would be optional. Line 21 also
 * contains a compiler error. While an annotation can be used in a cast operation, it requires a type. If the cast
 * expression was changed to (@Floats boolean), then it would compile. The rest of the code compiles without issue.
 *
 */
@Floats
public class Birch implements @Floats Wood {
    @Floats(10) boolean mill(){
        Predicate<Integer> t = (@Floats Integer a) -> a > 10;
        return (@Floats) t.test(12);
    }
}
