package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadLineStreamExample {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("/fish/sharks.log");
        try (var s = Files.lines(path)) {
            s.filter(f -> f.startsWith("WARN"))
                    .map(f -> f.substring(5))
                    .forEach(System.out::println);
        }
    }
}
