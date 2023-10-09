package chapter_1.enum_modifier;

public class SeasonV2Test {

    /**
     * How do we call an enum method?
     * Notice how we don't appear to call the constructor. We just say that we want the enum value. The first time that
     * we ask for any of the enum values, Java constructs all of the enum values. After that, Java just returns the already
     * constructed enum values. Given that explanation, you can see why this calls the constructor only once:
     */
    public static void main(String[] args) {
        SeasonV2.SUMMER.printExpectedVisitors();
    }
}
