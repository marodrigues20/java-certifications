package chapter_2.array_values;


/**
 * The first annotation is considered the regular form, as it is clear the usage is for an array. The second annotation
 * is the shorthand notation, where the array braces ({}) are dropped for convenience. Keep in mind that this providing
 * a value for an array element; The compiler is just inserting the missing array braces for you.
 */
public class Giraffe {
    @Music(genres = {"Rock and roll"})
    String mostDisliked;
    @Music(genres = "Classical")
    String favorite;
}
