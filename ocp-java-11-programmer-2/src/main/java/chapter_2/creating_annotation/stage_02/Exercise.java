package chapter_2.creating_annotation.stage_02;

/**
 * The syntax for the hoursPerDay() element may seen a little strange at first. It looks a lot like an abstract method,
 * although we're calling it an element (or attribute). Remember, annotation have their roots in interfaces. Behind the
 * scenes, the JVM is creating elements as interface methods and annotations as implementations of these interfaces.
 */
public @interface Exercise {
    int hoursPerDay();
    int startHour() default 6;
}
