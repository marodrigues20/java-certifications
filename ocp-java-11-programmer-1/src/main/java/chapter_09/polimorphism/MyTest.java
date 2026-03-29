package chapter_09.polimorphism;

public class MyTest {

    public static void main(String[] args) {

        Parent p = new Child();
        System.out.println(p.name);   // "Parent"  ← variável, compile time
        System.out.println(p.who());  // "Child"   ← método, runtime (override)

    }
}
