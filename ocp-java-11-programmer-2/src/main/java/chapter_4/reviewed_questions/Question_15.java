package chapter_4.reviewed_questions;


import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given the generic type String, the partitioningBy() collector creates a Map<Boolean, List<String>
 * when passed to collect() by default.
 * When a downstream collector is passed to partitioningBy(), which return types can be created?
 * (Choose all that apply.)
 *
 * Answer B, D.
 *
 * Options A and C do not compile, because they are invalid generic declarations.
 * Primitives are not allowed as generics, and Map must have two generic type parameters.
 * Options E is incorrect because partitioning only gives a Boolean key.
 * Options B and D are correct because they return a Map with a Boolean key and a value type that can be customized to
 * any Collection.
 */
public class Question_15 {

    public static void main(String[] args) {

      //B  Map<Boolean, List<String>> x;

      //D  Map<Boolean, Set<String>> j;
    }
}
