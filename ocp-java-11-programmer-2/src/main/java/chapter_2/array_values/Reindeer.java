package chapter_2.array_values;


/**
 * Line 8, Provides on more value, while the next two do not provide any values.
 * Line 17, It compiles, as an array with no elements is still a valid array.
 *
 * While this shorthand notation can be used for arrays, it does not work for List or Collection. As mentioned earlier,
 * they are not in the list of supported elements types for annotations.
 */
public class Reindeer {
    //@Music(genres = "Blues", "Jazz") //DOES NOT COMPILE
    String favorite;
    //@Music(genres = )                // DOES NOT COMPILE
    String mostDisliked;
    //@Music(genres = null)            // DOES NOT COMPILE
    String other;
    @Music(genres = {})
    String alternate;
}
