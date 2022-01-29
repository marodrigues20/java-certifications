package chapter_3.method.refmethodstatic;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Calling static method
 */
public class Sort {

    public static void main(String[] args) {
        Consumer<List<Integer>> methodRef = Collections::sort;
        Consumer<List<Integer>> lambda = x -> Collections.sort(x);
    }
}
