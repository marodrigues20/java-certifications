package chapter_3.generics.interfaces;


import java.util.ArrayList;

/**
 * Second way to implement Shippable
 * The type parameter could use T. We used U in the example so that it isn't confusing as to what T refers to.
 * @param <U>
 */
public class ShippableAbstractCrate<U> implements Shippable<U> {


    @Override
    public void ship(U u) {

    }
}
