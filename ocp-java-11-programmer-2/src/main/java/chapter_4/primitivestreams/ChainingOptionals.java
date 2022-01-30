package chapter_4.primitivestreams;

import java.util.Optional;

public class ChainingOptionals {

    public static void main(String[] args) {

        threeDigitsWithoutFunctionalProgramming(Optional.of(100));
        threeDigitsWithFucntionalProgramming(Optional.of(100));

    }

    private static void threeDigitsWithFucntionalProgramming(Optional<Integer> optional) {
        optional.map(n -> "" + n)
                .filter(s -> s.length() == 3)
                .ifPresent(System.out::println);
    }

    private static void threeDigitsWithoutFunctionalProgramming(Optional<Integer> optional) {
        if (optional.isPresent()){
            var num = optional.get();
            var string = "" + num;
            if (string.length() == 3) // inner if
                System.out.println(string);
        }

    }
}
