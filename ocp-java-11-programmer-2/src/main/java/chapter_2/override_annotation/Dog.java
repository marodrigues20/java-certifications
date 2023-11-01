package chapter_2.override_annotation;


/**
 * The playFetch() method does not compile, because there is no inherited method
 * with that name.
 * In the Dog class, howl() is an overloaded method, not an overridden one.
 */
public class Dog extends Canine {

    //@Override // DOES NOT COMPILE
    public boolean playFetch(){
        return true;
    }

    //@Override // DOES NOT COMPILE
    void howl(int timeOfDay) {}
}
