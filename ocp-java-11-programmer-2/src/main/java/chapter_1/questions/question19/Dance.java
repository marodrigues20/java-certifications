package chapter_1.questions.question19;

public interface Dance {
    default void perform(){
        System.out.println("Dance!");
    }
}
