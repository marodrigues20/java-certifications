package chapter_5.reviewed_questions.question_13;


import java.util.Locale;

/**
 * 13. Which of the following, when inserted independently in the blank, use locale parameter that are properly formatted?
 * (Choose all that apply)
 * Correct answer: C and D
 *
 * The code compiles with the appropriate input, so option G is incorrect.
 * A locale consists of a required lowercase language code and optional uppercase country code.
 * In the Locale() constructor, the language code is provided first.
 * For these reasons, options C and D are correct.
 * Option E and F are incorrect because a Locale is created using a constructor or Locale.Builder class.
 */
public class ReadMap implements AutoCloseable{

    private Locale locale;

    private boolean closed = false;

    void check(){
        assert !closed;
    }

    @Override
    public void close() {
        check();
        System.out.println("Folding map");
        locale = null;
        closed = true;
    }

    public void open(){
        check();
        this.locale = new Locale("wp", "VW");
    }

    public void use(){
        // Implementation omitted
    }

}
