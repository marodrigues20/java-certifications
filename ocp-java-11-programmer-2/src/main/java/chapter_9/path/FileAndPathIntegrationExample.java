package chapter_9.path;

import java.io.File;
import java.nio.file.Path;

public class FileAndPathIntegrationExample {

    public static void main(String[] args) {
        File file = new File("husky.png");
        Path path = file.toPath();
        File backToFile = path.toFile();
    }
}
