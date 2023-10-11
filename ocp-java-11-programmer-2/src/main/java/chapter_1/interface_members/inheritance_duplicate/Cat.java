package chapter_1.interface_members.inheritance_duplicate;

/**
 * If a class implements two interfaces that have default methods with the same method signature, the compiler will report an error.
 * This rule holds true even for abstract classes because the duplicate method could be called within a concrete method within
 * the abstract class.
 */
public class Cat implements Walk, Run{

    /**
     * by overriding  the conflicting method, the ambiguity about which version of the method to call has been removed.
     * @return
     */
    @Override
    public int getSpeed() {
        return 1;
    }


    /**
     * You can't call Walk.getSpeed(). A default method is treated as part of the instance since they can be overridden,
     * so they cannot be called like a static method.
     * @return
     */
    public int getWalkSpeed(){
        return Walk.super.getSpeed();
    }

    public static void main(String[] args) {
        System.out.println(new Cat().getSpeed());
    }
}
