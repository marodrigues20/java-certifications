package chapter_7.streams;

import java.util.List;

/**
 * Creating Unordered Stream
 *
 * All of the stream with which you have been working are considered ordered by default. It is possible to create an
 * unordered stream from an ordered stream.
 *
 * This method does not actually reorder the elements; its just tell the JVM that if an order-based stream operation
 * is applied, the order can be ignored.
 *
 * For serial streams, using an unordered version has no effect, but on parallel stream, the results can greatly improve
 * performance.
 */
public class UnorderedStream {

    public static void main(String[] args) {
        List.of(1,2,3,4,5,6).stream().unordered();

        List.of(1,2,3,4,5,6).stream().unordered().parallel();
    }
}
