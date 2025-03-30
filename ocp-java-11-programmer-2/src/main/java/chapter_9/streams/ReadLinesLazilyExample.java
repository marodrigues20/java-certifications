package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadLinesLazilyExample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/fish/sharks.log");
        try (var s = Files.lines(path)){
            s.forEach(System.out::println);
        }
    }
}
