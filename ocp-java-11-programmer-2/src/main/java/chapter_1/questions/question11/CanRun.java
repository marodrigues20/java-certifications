package chapter_1.questions.question11;

public interface CanRun {

    abstract public void run();

    private void testWalk(){}

    default void walk() {
        System.out.println("Running");
    }
}
