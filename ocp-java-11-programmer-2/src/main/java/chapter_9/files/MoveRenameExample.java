package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MoveRenameExample {

    public static void main(String[] args) throws IOException {

        Files.move(Path.of("c:\\zoo"), Path.of("c:\\zoo-new"));

        Files.move(Path.of("c:\\user\\addresses.txt"),
                Path.of("c:\\zoo-new\\addresses2.txt"));
    }
}
