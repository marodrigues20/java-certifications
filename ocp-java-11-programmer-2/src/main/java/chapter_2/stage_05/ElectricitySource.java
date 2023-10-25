package chapter_2.stage_05;


/**
 * Just like interfaces variables, annotation variables are implicitly public, static, final. These constants variables
 * are not considered elements, though. For example, marker annotations can contain constants.
 */
public @interface ElectricitySource {

    public int voltage();

    int MIN_VOLTAGE = 2; // Constants variables are not considered elements.

    public static final int MAX_VOLTAGE = 18;
}
