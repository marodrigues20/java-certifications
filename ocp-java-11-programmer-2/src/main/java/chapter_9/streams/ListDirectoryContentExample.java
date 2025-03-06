package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ListDirectoryContentExample {

    public static void main(String[] args) throws IOException {
        try(Stream<Path> s = Files.list(Path.of("src/main/resources"))){
            s.forEach(System.out::println);
        }
    }
}
