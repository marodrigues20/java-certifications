package chapter_3.generics.bound;

import java.util.List;


/**
 * List<?> - Unbounded generics are immutable
 * List<? extends Object> - Upper bounded generics are immutable
 * List<? super String> - Lower bounded
 */
public class Goose implements Flyer{
    @Override
    public void fly() {

    }

    private void anyFlyer(List<Flyer> flyer){}
    private void groupOfFlyers(List<? extends Flyer> flyer){

    }
}
