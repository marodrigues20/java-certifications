package chapter_4.optional;

import java.util.Optional;


/**
 * When create a Optional is common to want to use empty() when value is null.
 */
public class OptionalExample {

    public static void main(String[] args) {
        System.out.println(average(90, 100));
        System.out.println(average());

        Optional<Double> opt = average(90, 100);
        if(opt.isPresent())
            System.out.println(opt.get());

        Optional<Double> opt2 = average();
        //System.out.println(opt2.get()); //NoSuchElementException: No value present

        createOptional(Double.valueOf(6));

        Optional<Double> opt3 = average(90,100);
        opt3.ifPresent(System.out::println);

        Optional<Double> opt4 = average();
        System.out.println(opt4.orElse(Double.NaN));
        System.out.println(opt4.orElseGet(() -> Math.random()));

        Optional<Double> opt5 = average();
        //opt5.orElseThrow(); //NoSuchElementException("No value present");

        Optional<Double> opt6 = average();
        //opt6.orElseThrow(() -> new IllegalStateException()); //IllegalStateException Exception

        Optional<Double> opt7 = average(90, 100);
        System.out.println(opt7.orElse(Double.NaN));
        System.out.println(opt7.orElseGet(() -> Math.random()));
        System.out.println(opt.orElseThrow());

    }

    private static Optional<Double> average(int... scores){
        if(scores.length == 0)
            return Optional.empty();
        int sum = 0;
        for (int score: scores) sum+= score;
            return Optional.of((double) sum / scores.length);
    }

    private static Optional<Double> createOptional(Double value){
        Optional<Double> o = (value == null) ? Optional.empty() : Optional.of(value);
        return o;
    }
}
