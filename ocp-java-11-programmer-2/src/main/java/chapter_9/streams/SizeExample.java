package chapter_9.streams;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;

public class SizeExample {

    private long getSize(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            // Handle exception
        }

        return 0L;
    }

    public long getPathSize(Path source) throws IOException {
        try (var s = Files.walk(source)) {
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }

    public long getPathSizeFollowLinks(Path source) throws IOException {
        try (var s = Files.walk(source,
                FileVisitOption.FOLLOW_LINKS)) {
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }

}


