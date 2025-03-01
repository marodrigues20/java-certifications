package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LastModifiedTimeExample {

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get("animals/bear");
        System.out.println(Files.getLastModifiedTime(path).toMillis());
    }
}
