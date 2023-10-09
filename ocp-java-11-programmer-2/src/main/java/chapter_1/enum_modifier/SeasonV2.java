package chapter_1.enum_modifier;

/**
 * Enums can have more in them than just a list of values. Let's say our zoo wants to keep track of traffic patterns
 * for which seasons get the most visitors.
 */
public enum SeasonV2 {

    /**
     * Pay attention, when we have more than just values in enum.
     * We need to use semi-colon at the end of the declaration.
     */
    WINTER("LOW"), SPRING("Medium"), SUMMER("High"), FALL("Meddium");

    /**
     * We mark the instance variable final on line 18 so that our enum values are considered immutable.
     * Although this is certainly not required, it is considered a good coding practice to do so.
     * Since enum values are shared by all process in the JVM, it would be problematic if one of them could change
     * the value inside an enum.
     */
    private final String expectedVisitors; // We mark as a final to consider our attributes are immutable. It is not required but it is
                                           //good practice.

    /**
     * All enum constructor are implicitly private, with the modifier being optional.
     * This is the reason why you can't extend an enum and the constructor can be called only within the enum itself.
     * If you change the constructor to public or protect the enum constructor will compile.
     * @param expectedVisitors
     */
    private SeasonV2(String expectedVisitors){
        this.expectedVisitors = expectedVisitors;
    }

    public void printExpectedVisitors(){
        System.out.println(expectedVisitors);
    }


}
