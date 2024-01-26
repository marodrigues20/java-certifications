package chapter_5.reviewed_questions.question_19;


import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 19. Suppose that we have the following three properties files and code.
 * Which bundles are used on lines 8 and 9, respectively?
 *
 * ```
 * Dolphins.properties
 * name=The Dolphin
 * age=0
 *
 * Dolphins_en.properties
 * name=Dolly
 * age=4
 *
 * Dolphins_fr.properties
 * name=Dolly
 * ```
 *
 * Answer: D
 *
 * Java will use Dolphins_fr.properties as the matching resource bundle on line 40 because it is an exact match on the
 * language of the requested locale.
 * Line 8 finds a matching key in this file.
 * Line 9 does not find a match in that file; therefore, it has to look higher up in the hierarchy are allowed.
 * It cannot use the default locale anymore, but it can use the default resource bundle specified by Dolphins.properties.
 *
 *
 */
public class Question_19 {

    public static void main(String[] args) {

        var fr = new Locale("fr");
        Locale.setDefault(new Locale("en", "US"));
        var b = ResourceBundle.getBundle("Dolphins", fr);
        b.getString("name");
        b.getString("age");
    }
}
