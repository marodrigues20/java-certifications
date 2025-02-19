package chapter_9.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyFileExample {

    public static void main(String[] args) throws IOException {

        try (var is = new FileInputStream("source-data.txt")) {
            // Write stream data to a file
            Files.copy(is, Paths.get("mammals/wolf.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Files.copy(Paths.get("fish/clown.xsl"), System.out);
    }
}


