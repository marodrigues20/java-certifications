package chapter_3.method.refmethodparam;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Instance Method on a Parameter
 */
public class MethodParam {

    public static void main(String[] args) {

        //It doesn't take any parameter
        Predicate<String> methodRef = String::isEmpty;
        Predicate<String> lambda = s -> s.isEmpty();

        BiPredicate<String, String> methodRef2 = String::startsWith;
        //The first parameter is the instance of object the second one is the parameter
        BiPredicate<String, String> lambda2 = (s, p) -> s.startsWith(p);
    }
}
