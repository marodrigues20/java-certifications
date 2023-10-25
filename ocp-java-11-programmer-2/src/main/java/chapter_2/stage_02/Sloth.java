package chapter_2.stage_02;


/**
 * The Sloth class does not compile because it is missing parentheses around the annotation parameters. Remember,
 * parentheses are optional only if no values are included.
 *
 * //@Exercise hoursPerDay = 0  // DOES NOT COMPILE
 */

@Exercise(hoursPerDay = 0)  // We don't specify a value for startHour, meaning it will be instantiated with the default value of 6.
public class Sloth {
}


