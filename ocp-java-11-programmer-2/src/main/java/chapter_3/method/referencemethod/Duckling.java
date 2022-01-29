package chapter_3.method.referencemethod;

public class Duckling {

    public static void makeSound(String sound){
        LearnToSpeak learner = s -> System.out.println(s);
        //Method Reference - :: operator tells Java call the println() method later.
        LearnToSpeak learn = System.out::println;
        DuckHelper.teacher(sound, learner);
    }
}
