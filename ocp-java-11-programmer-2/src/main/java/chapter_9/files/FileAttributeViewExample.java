package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileAttributeViewExample {

    public static void main(String[] args) throws IOException {

        // Read file attributes
        var path = Paths.get("src/main/resources/turtle/sea.txt");
        BasicFileAttributeView view = Files.getFileAttributeView(path,
                BasicFileAttributeView.class);
        BasicFileAttributes attributes = view.readAttributes();

        // Modify file last modified time
        FileTime lastModifiedTime = FileTime.fromMillis(
                attributes.lastModifiedTime().toMillis() + 10_000);
        view.setTimes(lastModifiedTime, null, null);

    }
}
