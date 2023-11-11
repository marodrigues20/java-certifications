package chapter_2.review_questions.question_24;


/**
 * Question 24
 * Correct is A and E.
 *
 * The annotation includes only one required element, and it is named value(), so it can be used without an element
 * name provided it is the only value in the annotation. For this reason, option A is correct, and option B and D are
 * incorrect. Since the type of the value() is an array, option B would work if it was changed to @Dance({33, 10}).
 * Option C is incorrect because it attempts to assign a value to fast, which is a constant variable not an element.
 * Option E is correct and is an example of an annotation replacing all of the optional values. Option F is incorrect,
 * as value() is a required element.
 */
public class Sing {

    //@Dance(77)
    @Dance(value = 5, rhythm = 2,track = "Samba")
    String album;
}
