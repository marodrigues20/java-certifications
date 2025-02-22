package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class NewBufferedWriterExample {

    public static void main(String[] args) {
        var list = new ArrayList<String>();
        list.add("Smokey");
        list.add("Yogi");

        var path = Path.of("animals/bear");
        try (var writer = Files.newBufferedWriter(path)) {
            for (var line : list) {
                writer.write(line);
                writer.newLine();
                ;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
