package chapter_1.questions.question11;

public interface CanWalk {
    public default void walk(){
        System.out.println("Walking");
    }
    private void testWalk(){}
}
