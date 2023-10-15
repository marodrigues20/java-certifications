package chapter_1.functional_programming.functional_interfaces_lambda;

public class Animal {

    private String species;
    private boolean canHop;
    private boolean canSwim;

    public Animal(String species, boolean canHop, boolean canSwim) {
        this.species = species;
        this.canHop = canHop;
        this.canSwim = canSwim;
    }

    public boolean canHop() { return canHop; }
    public boolean toSwim() { return canSwim; }
    public String toString() { return species; }
}
