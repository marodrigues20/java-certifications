package chapter_08.inherite_object;


/**
 * Toda classe em Java é filha de Object. Se fizer override de equals(), a boa prática é também fazer override de hashCode().
 */
public class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dog)) return false;
        Dog other = (Dog) obj;
        return this.name.equals(other.name);
    }

    @Override
    public String toString() {
        return "Dog{name=" + name + "}";
    }
}

