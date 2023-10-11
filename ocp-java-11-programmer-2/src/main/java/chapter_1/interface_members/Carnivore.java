package chapter_1.interface_members;

public interface Carnivore {

    /**
     * Marked as default but does't provide a method body
     */
    //public default void eatMeat(); // DOES NOT COMPILE


    /**
     * Not marked as default
     */
    /*public int getRequiredFoodAmount(){ // DOES NOT COMPILE
        return 13;
    }*/
}
