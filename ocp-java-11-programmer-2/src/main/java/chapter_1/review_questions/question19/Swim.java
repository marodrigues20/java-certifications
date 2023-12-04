package chapter_1.review_questions.question19;

public interface Swim {
    default void perform(){
        System.out.println("Swim!");
    }
}
