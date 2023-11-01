package chapter_2.surpresswarnings_annotation;

import java.util.ArrayList;


/**
 * This cod compiles and runs but prodeuces two compiler warnings.
 *   Nighingale.java uses or overrides a deprecated API.
 *   Nighingale.java uses unchecked or unsafe operations.
 *
 * The first warning is because we are using a method SongBird.sing() that is deprecated. The second warning is triggered
 * by the call to new ArrayList(), which does not define a generic type. An improved implementation would be to use
 * new ArrayList<String>().
 *
 * Let's say we are absolutely sure that we don't want to change our Nighingale implementation, and we don't want the
 * compiler to bother us anymore about these warnings. Adding the @SuppressWarnings annotation, with the correct values,
 * accomplishes this.
 */
public class Nightingale {

    @SuppressWarnings("deprecation")
    public void wakeUp(){
        SongBird.sing(10);
    }

    @SuppressWarnings("unchecked")
    public void goToBed(){
        SongBird.chirp(new ArrayList());
    }

    public static void main(String[] args) {
        var birdy = new Nightingale();
        birdy.wakeUp();
        birdy.goToBed();
    }
}
