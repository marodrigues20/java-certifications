package chapter_1.interface_members;

/**
 * The following is an example of a default method defined in an interface.
 * This example defines two interface method: one is the abstract hasScales() method, and the other is the default
 * getTemperature() method. Any class that implements IsWarmBlooded may rely on the default implementation of
 * getTemperature() or override the method with its own version. Both of these methods include the implicit
 * public modifier, so overriding them with a different access modifier is not allowed.
 */
public interface IsWarmBlooded {
    boolean hasScaled();
    default double getTemperature(){
        return 10.0;
    }
}
