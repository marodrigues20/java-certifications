package chapter_8.stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Assume that we have an InputStream instance whose next value are LION. Consider the following code snipped:
 */
public class markMethodExample {

    public static void main(String[] args) throws IOException {
        readData(new FileInputStream("xpto.xml"));
    }

    private static void readData(InputStream is) throws IOException {
        System.out.println((char) is.read()); //L
        if(is.markSupported()){
            is.mark(100); //Marks up to 100 bytes
            System.out.println((char) is.read());
            System.out.println((char) is.read());
            is.reset(); //Resets stream to position before I
        }
        System.out.println((char) is.read()); // I
        System.out.println((char) is.read()); // O
        System.out.println((char) is.read()); // N

    }


}
