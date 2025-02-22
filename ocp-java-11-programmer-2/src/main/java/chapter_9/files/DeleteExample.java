package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteExample {

    public static void main(String[] args) throws IOException {
        Files.delete(Paths.get("/vulture/feathers.txt"));
        Files.deleteIfExists(Paths.get("/pigeon"));
    }
}
