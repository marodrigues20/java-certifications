package chapter_5.assertions;

public class Syntax {

    public static void main(String[] args) {

        int age = 1;
        int height = 2;
        double lenght = 100.00;
        String name = "Cecelia";

        assert 1 == age;

        assert(2 == height);

        assert 100.0 == lenght : "Problem with length";

        assert ("Cecelia".equals(name)) : "Failed to verify user data";

    }
}
