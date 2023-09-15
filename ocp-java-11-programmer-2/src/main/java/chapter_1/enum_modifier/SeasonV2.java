package chapter_1.enum_modifier;

public enum SeasonV2 {

    WINTER("LOW"), SPRING("Medium"), SUMMER("High"), FALL("Meddium");

    private final String expectedVisitors; // We mark as a final to consider our attributes are immutable. It is not required but it is
                                           //good practice.

    private SeasonV2(String expectedVisitors){
        this.expectedVisitors = expectedVisitors;
    }

    public void printExpectedVisitors(){
        System.out.println(expectedVisitors);
    }


}
