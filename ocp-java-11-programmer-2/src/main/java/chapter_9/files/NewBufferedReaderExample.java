package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NewBufferedReaderExample {

    public static void main(String[] args) throws IOException {
        var path = Path.of("/animals/gopher.txt");
        try(var reader = Files.newBufferedReader(path)){
            String currentLine = null;
            while((currentLine = reader.readLine()) != null){
                System.out.println(currentLine);
            }
        }
    }
}
