package chapter_2.creating_annotation.stage_02;


/**
 * This class does not compile because the hoursPerDay field is required. Remember, annotation itself is optional, the
 * compiler still cares that they are used correctly.
 * When declaring an annotation, any element without a default value is considered required.
 *
 * //@Exercise  // DOES NOT COMPILE
 */

//@Exercise(hoursPerDay = 7, startHour = "8") // DOES NOT COMPILE
public class ZooEmployee {
}
