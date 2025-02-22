package chapter_9.files;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonAttributeExample {

    public static void main(String[] args) {
        System.out.println(Files.isDirectory(Paths.get("/canine/fur.jpg")));
        System.out.println(Files.isSymbolicLink(Paths.get("/canine/coyote")));
        System.out.println(Files.isRegularFile(Paths.get("/canine/types.txt")));
    }
}
