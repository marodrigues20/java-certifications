package chapter_08.inherite_object;

public class MyTest {

    public static void main(String[] args) {


        Dog d = new Dog("Rex");
        System.out.println(d); // Dog{name=Rex}

        Dog d1 = new Dog("Rex");
        Dog d2 = new Dog("Rex");

        System.out.println(d1 == d2);     // false — different references
        System.out.println(d1.equals(d2)); // true — same content


    }
}
