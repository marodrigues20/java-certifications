package chapter_9.compare.domain;

public class Dog extends Animal implements Comparable<Dog> {

    private String name;
    private Integer age;
    private BreedEnum breed;

    public Dog(String name, Integer age, BreedEnum breed) {
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public BreedEnum getBreed() {
        return breed;
    }

    /**
     * Defines the natural ordering of Dog by breed (Enum declaration order).
     * Natural order is ascending — from the lowest ordinal to the highest.
     * Enum.compareTo() returns the difference between ordinal values.
     *
     * Returns negative -> this comes BEFORE the other dog (this.breed ordinal < dog.breed ordinal)
     * Returns zero     -> both dogs have the same breed
     * Returns positive -> this comes AFTER the other dog (this.breed ordinal > dog.breed ordinal)
     *
     * @param dog the object to be compared.
     * @return negative, zero, or positive integer
     */
    @Override
    public int compareTo(Dog dog) {
        return this.breed.compareTo(dog.breed);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", breed=" + breed +
                '}';
    }
}
