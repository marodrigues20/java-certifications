package chapter_2.value_element;


public abstract class Elephant {
    @Injured("Legs")
    public void fallDown(){}
    @Injured("Legs")
    public abstract int trip();
    @Injured
    String injuries[];
}
