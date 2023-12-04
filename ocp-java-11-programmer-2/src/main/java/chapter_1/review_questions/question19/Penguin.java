package chapter_1.review_questions.question19;


/**
 * Question 19.
 * Letter D is the correct answer.
 * First off, if a class or interface inherits two interfaces containing default methods with the same signature, them
 * it must override the method with its own implementation. The Penguin class does this correctly, so option E is
 * incorrect. The way to access an inherited default method is by suing the syntax Swim.super.perform(), making option
 * D correct. We agree the syntax is bizarre, but you need to learn it. Options A, B, C are incorrect and result in
 * compiler errors.
 */
public class Penguin implements Swim, Dance {
    public void perform() {
        System.out.println("Smile!");
    }

    private void doShow() {
        Swim.super.perform();
    }

    public static void main(String[] eggs) {
        new Penguin().doShow();
    }

}
