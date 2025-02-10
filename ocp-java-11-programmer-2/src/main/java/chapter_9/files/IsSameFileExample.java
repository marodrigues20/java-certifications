package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IsSameFileExample {

    public static void main(String[] args) throws IOException {

        System.out.println(Files.isSameFile(
                Path.of("/animals/cobra"),
                Path.of("/animal/snake")));

        System.out.println(Files.isSameFile(
                Path.of("/animals/monkey/ears.png"),
                Path.of("/animal/wolf/ears.png")));
    }
}
