package chapter_8.assessment.question_19;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Question19 {

    public void copyFile(File file1, File file2) throws Exception {
        var reader = new InputStreamReader(new FileInputStream(file1));
        try (var writer = new FileWriter(file2)) {
            char[] buffer = new char[10];
            while (reader.read(buffer) != -1) {
                writer.write(buffer);
                // n1
            }
        }
    }
}
