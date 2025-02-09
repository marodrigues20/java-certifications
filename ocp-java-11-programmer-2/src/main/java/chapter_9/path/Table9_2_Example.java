package chapter_9.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Table9_2_Example {

    void copy(Path source, Path target) throws IOException {
        Files.move(source, target, LinkOption.NOFOLLOW_LINKS, StandardCopyOption.ATOMIC_MOVE);
    }
}
