package chapter_3.review_questions;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Question 13
 *
 * Correct are B and E.
 * The showSize() method can take any type of List since it uses an unbounded wildcard.
 * Option A is incorrect because it is a Set and not a List.
 * Option C is incorrect because the wildcard is not allowed to be on the right side of an assignment.
 * Option D is incorrect because the generic types are not compatible
 */
public class Wildcard {

    public void showSize(List<?> list){
        System.out.println(list.size());
    }

    public static void main(String[] args) {
        Wildcard card = new Wildcard();

        //List<?> list = new HashSet<String>();
        ArrayList<? super Date> list = new ArrayList<>();

        //List<?> list = new ArrayList<?>();

        //List<Exception list = new LinkedList<java.io.IOException>();

        ArrayList <? extends Number> list2 = new ArrayList<Integer>();

        card.showSize(list);
    }
}
