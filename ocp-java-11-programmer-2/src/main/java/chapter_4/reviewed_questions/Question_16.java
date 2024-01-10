package chapter_4.reviewed_questions;


import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Which of the following statements are true about this code? (Choose all that apply.)
 * Answer B, C.
 *
 * First, this mess of code does compile. While this code starts out with an infinite stream on line 40, it does become
 * finite on line 41 thanks to limit(), thanks to limit(), making option F incorrect. The pipeline preserves only
 * nonempty elements on line 42. Since there aren't any of those, the pipeline is empty. Line 43 converts this to an
 * empty map.
 *
 * Lines 44 and 45 create a Set with no elements and then another empty stream.
 * Lines 46 and 47 convert the generic type of the Stream to List<String> and then String.
 * Finally, line 48 gives us another Map<Boolean, List<String>>.
 *
 * The partitioningBy operation always returns a map with two Boolean keys, even if there are no corresponding values.
 * Therefore, option B is correct if the code is kept as is. By contrast, groupingBy() returns only keys that are
 * actually needed, making option C correct if the code is modified on line 65.
 */
public class Question_16 {

    public static void main(String[] args) {

        //answerB();
        answerC();

    }

    private static void answerB() {
        Predicate<String> empty = String::isEmpty;
        Predicate<String> notEmpty = empty.negate();

        var result = Stream.generate(() -> "")
                .limit(10)
                .filter(notEmpty)
                .collect(Collectors.groupingBy(k -> k))
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.partitioningBy(notEmpty));

        System.out.println(result); //{false=[], true=[]}
    }

    private static void answerC() {
        Predicate<String> empty = String::isEmpty;
        Predicate<String> notEmpty = empty.negate();

        var result = Stream.generate(() -> "")
                .limit(10)
                .filter(notEmpty)
                .collect(Collectors.groupingBy(k -> k))
                .entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(n -> n));

        System.out.println(result); // {}
    }
}
