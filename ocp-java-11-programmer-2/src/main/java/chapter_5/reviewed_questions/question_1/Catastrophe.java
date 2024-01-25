package chapter_5.reviewed_questions.question_1;


public class Catastrophe extends Exception{

    public Catastrophe(Throwable c) throws RuntimeException{
        super(new Exception());
        c.printStackTrace();
    }
}
