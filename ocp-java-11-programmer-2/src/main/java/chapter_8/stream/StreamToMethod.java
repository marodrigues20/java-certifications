package chapter_8.stream;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * What about if you need to pass a stream to a method? That's fine, but the stream should be closed in the method that
 * created it.
 *
 * In this example, the stream is created and closed in the readFile() method, with the printData() processing the
 * contents.
 */
public class StreamToMethod {

    public static void main(String[] args) throws IOException {
        StreamToMethod streamToMethod = new StreamToMethod();
        streamToMethod.readFile("pom.xml");

    }

    public void readFile(String fileName) throws IOException {
        try (var fis = new FileInputStream(fileName)) {
            printData(fis);
        }
    }

    private void printData(FileInputStream fis) throws IOException {
        int b;
        while((b = fis.read()) != -1){
            System.out.println(b);
        }
    }

}
