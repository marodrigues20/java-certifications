package chapter_3.method.refmethodinstance;

import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Instance Method on a Particular Object
 */
public class MethodReferences {

    public static void main(String[] args) {
        String str = "abs";
        Predicate<String> methodRef = str::startsWith;
        Predicate<String> lambda = s -> str.startsWith(s);

        var random = new Random();
        Supplier<Integer> methodRef2 = random::nextInt;
        Supplier<Integer> lambda2 = () -> random.nextInt();

    }
}
