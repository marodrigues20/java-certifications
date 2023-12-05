package chapter_3.review_questions;

import java.util.*;


/**
 * Question 5
 *
 * Correct B and F.
 * Option A does not compile because the generic types are not compatible.
 * We would  say HashSet<? extends Number> hs2 = new HashSet<Integer>();
 * Option B uses a lower bound, so it allows superclass generic types.
 * Option C does not compile because the diamond operator is allowed only on the right side.
 * Option D does not compile because a Set is not a List.
 * Option E does not compile because upper bound are not allowed when instantiating the type.
 * Option F does compile because the upper bound is non the correct side of the =.
 */
public class Question_5 {

    public static void main(String[] args) {

        //HashSet<Number> hs = new HashSet<Integer>();

        HashSet<? super ClassCastException> set = new HashSet<Exception>();

        //List<> list = new ArrayList<String>();

        //List<Object> values = new HashSet<Object>();

        //List<Object> objects = new ArrayList<? extends Object>();

        Map<String, ? extends Number> hm = new HashMap<String, Integer>();
    }
}
