package chapter_8.stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlushMethodExample {

    public static void main(String[] args) {

        try (var fos = new FileOutputStream("alex.txt")) {
            for (int i = 0; i < 1000; i++) {
                fos.write('a');
                if (i % 100 == 0) {
                    fos.flush();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
