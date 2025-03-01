package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SizeExample {

    public static void main(String[] args) throws IOException {
        System.out.println(Files.size(Paths.get("/zoo/animals.txt")));
    }
}
