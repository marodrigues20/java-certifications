package chapter_9.path.methods;

import java.io.IOException;
import java.nio.file.Paths;

public class ToRealPathExample {

    public static void main(String[] args) throws IOException {

        System.out.println(Paths.get("/zebra/food.txt").toRealPath());
        System.out.println(Paths.get(".././food.txt").toRealPath());

    }
}
