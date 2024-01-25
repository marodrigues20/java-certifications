package chapter_5.reviewed_questions.question_6;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * 6. Assume that all of the files mentioned in the answer choices exist and define the same keys.
 * Which one will be used to find the key in line 16?
 *
 * Correct Answer is: C
 *
 * Java will first look for the most specific matches it can find, starting with Dolphins_en_US.properties.
 * Since that is not an answer choice, it drops the country and looks for Dolphins_en.properties, making option C correct.
 * Option B is incorrect because a country without a language is not a valid locale.
 */
public class Question_6 {

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        var b = ResourceBundle.getBundle("Dolphins");
        System.out.println("name");
    }
}
