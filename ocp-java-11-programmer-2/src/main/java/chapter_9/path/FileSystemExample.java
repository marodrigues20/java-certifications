package chapter_9.path;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemExample {

    public static void main(String[] args) throws URISyntaxException {
        FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://wwww.selikoff.net"));
        Path path = fileSystem.getPath("duck.txt");
    }
}
