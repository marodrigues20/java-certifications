package chapter_1.enum_modifier;

public class EnumTest {


    public static void main(String[] args) {
        printEnums();
        retrieveAllValuesFromEnum();
        compareEnum();
        retrieveString();
    }


    /**
     * Retrieve an enum value from a String using the valueOf() method.
     */
    private static void retrieveString() {
        Season s = Season.valueOf("SUMMER"); // PRINT SUMMER
        Season t = Season.valueOf("summer"); //Throws an exception at runtime (java.lang.IllegalArgumentException)
    }


    /**
     * As you can see, enum print the name of the enum when toString() is called They can be compared using == because
     * they are like static final constants. In other words, you can use equals() or == to compare enums.
     */
    private static void printEnums() {
        Season s = Season.SUMMER;
        System.out.println(Season.SUMMER); // SUMMER
        System.out.println(s == Season.SUMMER); // true
    }


    /**
     * Each enum value has a corresponding int value, and the values are listed in the order in which they are declared.
     */
    private static void retrieveAllValuesFromEnum() {
        for (Season season: Season.values()){
            System.out.println(season.name() + " " + season.ordinal());
        }
    }

    /**
     * You can't compare an int and enum value directly anyway since an enum is a type, like a Java class, and not a
     * primitive int.
     */
    private static void compareEnum(){
        //if( Season.SUMMER == 2){} // DOES NOT COMPILE
    }
}
