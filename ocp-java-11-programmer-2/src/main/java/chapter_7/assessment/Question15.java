package chapter_7.assessment;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The collect() operation groups the animals into those that do and do not start with the letter p.
 * Note that there are four animals that do not start with the letter p. Note that there are four animals
 * that do not start with the letter p and three animals that do. The logical complement operator (!) before
 * that startsWith() method means that results are reversed, so the output is 3 4.
 */
public class Question15 {

    public static void main(String[] args) {
        var cats = Stream.of("loepard", "lynx", "ocelot", "puma").parallel();
        var bears = Stream.of("panda", "grizzly", "polar").parallel();
        var data = Stream.of(cats, bears).flatMap(s -> s)
                .collect(Collectors.groupingByConcurrent(
                        s -> !s.startsWith("p")
                ));
        System.out.println(data.get(false).size()
                + " " + data.get(true).size());
    }
}
