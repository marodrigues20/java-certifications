package chapter_4.advanced_stream_pipeline;

import java.util.ArrayList;
import java.util.Optional;

public class LinkingStream {

    public static void main(String[] args) {
        //linkStreamToTheUnderlyingData();
        threeDigitRegularWay(Optional.of(312));
        threeDigit(Optional.of(312));
    }

    private static void linkStreamToTheUnderlyingData(){
        var cats = new ArrayList<String>();
        cats.add("Annie");
        cats.add("Ripley");
        var stream = cats.stream();
        cats.add("KC");
        System.out.println(stream.count());
    }


    private static void threeDigitRegularWay(Optional<Integer> optional){
        if (optional.isPresent()) {  // outer if
            var num = optional.get();
            var string = "" + num;
            if (string.length() == 3) // inner if
                System.out.println(string);
        }
    }

    private static void threeDigit(Optional<Integer> optional) {
        optional.map(n -> "" + n)           // part 1
                .filter(s -> s.length() == 3)     // part 2
                .ifPresent(System.out::println);  // part 3
    }
}
