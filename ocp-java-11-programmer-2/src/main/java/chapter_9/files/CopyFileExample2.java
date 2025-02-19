package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyFileExample2 {

    public static void main(String[] args) throws IOException {
        var file = Paths.get("food.txt");
        var directory = Paths.get("enclosure/food.txt");
        Files.copy(file, directory);
    }
}
