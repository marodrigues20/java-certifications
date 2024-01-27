package chapter_5.reviewed_questions.question_24;

import java.util.Properties;

/**
 * What is the output of the following method if props contains
 * {veggies=brontosaurus, meat=velociraptor}?
 *
 * Correct answer is: E
 *
 * The Properties class defines a get() method that does not allow for a default value.
 * It also has a getProperty() method, which returns the default value if the key is not provided.
 */
public class Question_24 {

    private static void print(Properties props){
        /*System.out.println(props.get("veggies", "none")
        + " " + props.get("omni","none"));*/
    }
}
