package chapter_1.questions.question19;

public interface Swim {
    default void perform(){
        System.out.println("Swim!");
    }
}
