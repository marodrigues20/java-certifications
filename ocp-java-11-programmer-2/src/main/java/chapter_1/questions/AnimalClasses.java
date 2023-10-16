package chapter_1.questions;


/**
 * Question 23.
 * C and F are correct.
 * Enums are required to have a semicolon (;) after the list of values if there is anything else in the enum. Don't
 * worry, you won't be expected to track down missing semicolons on the whole exam - only on enum questions. For this
 * reason, line 5 should have a semicolon after it since it is the end fo the list of enums, making option F correct.
 * Enum constructors are implicitly private, making option C correct as well. The rest of the enum compiles without issue.
 *
 */
public enum AnimalClasses {

    MAMMAL(true), INVERTIBRATE(Boolean.FALSE), BIRD(false),
    REPTILE(false), AMPHIBIAN(false), FISH(false) {
        public int swim(){
            return 4;
        }
    }; // added semicolon to compile the code. Original version does not have it.

    final boolean hasHair;

    //public AnimalClasses(boolean hasHair){ original line
    private AnimalClasses(boolean hasHair){ //just to compile the code
        this.hasHair = hasHair;
    }

    public boolean hasHair() { return hasHair; }

    public int swim() { return 0; }

}
