package chapter_4.reviewed_questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Question 7.
 * We have a method that returns a sorted list without changing the original.
 * Which of the following can replace the method implementation to do the same with streams?
 * Answer F.
 *
 */
public class Question_7 {

    private static List<String> sort(List<String> list){
        var copy = new ArrayList<String>(list);
        Collections.sort(copy, (a, b) -> b.compareTo(a) );
        return copy;
    }

    private static List<String> streamReplace(List<String> list){
        return list.stream()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());
    }
}
