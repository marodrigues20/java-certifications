package chapter_2.safevarargs_annotation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * This code compiles, although it generates two compiler warnings. Both are related to type safety.
 *
 * [Line 15] Type safety: Potential heap pollution via varargs parameter carrot
 * [Line 25] Type safety: A generic array of List<Integer> is created for a varargs parameter
 *
 * We can remove both compiler warnings by adding the @SafeVarargs annotation to line 15
 */
public class NeverDoThis {

    //@SafeVarargs
    final int thisIsUnsafe(List<Integer>... carrot){
        Object[] stick = carrot;
        stick[0] = Arrays.asList("nope!");

        return carrot[0].get(0); //ClassCastException at runtime
    }

    public static void main(String[] args) {
        var carrot = new ArrayList<Integer>();
        new NeverDoThis().thisIsUnsafe(carrot);
    }
}
