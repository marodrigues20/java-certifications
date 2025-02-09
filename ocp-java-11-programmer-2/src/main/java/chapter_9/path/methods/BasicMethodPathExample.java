package chapter_9.path.methods;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicMethodPathExample {

    public static void main(String[] args) {
        Path path = Paths.get("/land/hippo/harry.happy");
        for (int i = 0; i < path.getNameCount(); i ++){
            System.out.println(" Element " + i + " is: " + path.getName(i));
        }
    }
}
