package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadAllLinesExamples {

    public static void main(String[] args) throws IOException {

        var path = Path.of("animals/gopher.txt");
        final List<String> lines = Files.readAllLines(path);
        lines.forEach(System.out::println);
    }
}
