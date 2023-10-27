package chapter_2.array_values;


/**
 * The first annotation provides all the details, while the last one applies both shorthand rules.
 */
public class Capybara {
    @Rhythm(value = {"Swing"})
    String favorite;
    @Rhythm(value = "R&B")
    String secondFavorite;
    @Rhythm("Classical")
    String mostDisliked;
    @Rhythm("Country")
    String lastDisliked;
}
