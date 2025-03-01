package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAccessibleExamples {

    public static void main(String[] args) throws IOException {

        System.out.println(Files.isHidden(Paths.get("/walrus.txt")));
        System.out.println(Files.isReadable(Paths.get("/seal/baby.png")));
        System.out.println(Files.isWritable(Paths.get("dolphin.txt")));
        System.out.println(Files.isExecutable(Paths.get("whales.png")));
    }
}
