package chapter_4.reviewed_questions;


/**
 *
 * Which of the following throw an exception when an Optional is empty?
 * (Choose all that apply.)
 * Answer C, E, F.
 *
 * Options A and B compile and return an empty string without throwing and exception, using a String and Supplier
 * parameter, respectively.
 * Option G does not compile as the get() method does not take a parameter.
 * Options C and F throw a NoSuchElementException.
 * Option E throws a RuntimeException.
 * Option D looks correct but will compile only if the throw is removed.
 * Remember, the orElseThrow() should get a lambda expression or method reference that returns and exception, not one
 * that throws an exception.
 */
public class Question_22 {

    // A. opt.orElse("");

    // B. opt.orElseGet(() -> "");

    // C. opt.orElseThrow(() -> throw new Exception());

    // D. opt.orElseThrow(() -> throw new Exception());

    // E. opt.orElseThrow(RuntimeException::new);

    // F. opt.get();

    // G. opt.get("");


}
