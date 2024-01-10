package chapter_4.reviewed_questions;


import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Which of the following is true? (Choose all that apply.)
 *
 * Answer: B, D.
 *
 * Line 23 creates a Stream and uses autoboxing to put the Integer wrapper of 1 inside.
 * Line 24 does not compile because boxed() is available only on primitive streams like IntStream, not Stream<Integer>.
 * Line 25 converts to a double primitive, which works since Integer can be unboxed to a value that can implicitly cast
 * to a double.
 * Line 26 does not compile for two reasons. First, converting from a double to an int would require an explicit cast.
 * Also, mapToInt() returns an IntStream so the data type of s3 is incorrect. The rest of the lines compile without
 * issue.
 */
public class Question_14 {
    public static void main(String[] args) {
        /*Stream<Integer> s = Stream.of(1);
        IntStream is = s.boxed();
        DoubleStream ds = s.mapToDouble(x -> x);
        Stream<Integer> s2 = ds.mapToInt(x -> x);
        s2.forEach(System.out::println);*/
    }
}
