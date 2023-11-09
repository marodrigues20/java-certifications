package chapter_2.review_questions.question_10;


/**
 * Question 10
 * G is correct. Furry is an annotation that can be applied only to types. In Bunny, it is applied to a method;
 * therefor, it does not compile. If the @Target value was changed to ElementType.METHOD(or @Target removed entirely),
 * then the rest of the code would compile without issue. The use of the shorthand notation for specifying a value() of
 * an array is correct.
 */
public class Bunny {

    //@Furry("Soft")
    public static int hop(){
        return 1;
    }
}
