package chapter_2.override_annotation;

public class Canine implements Intelligence{
    @Override
    public int cunning() {
        return 500;
    }

    void howl(){
        System.out.println("Woof!");
    }
}
