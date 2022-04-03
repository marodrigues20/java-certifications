package chapter_8.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * This code prints TRS at runtime. We skipped two characters, I and G. We also read E but didn't store it anywhere,
 * so it behaved like skip(1)
 */
public class SkipMethodExample {

    public static void main(String[] args) throws IOException {
        String str = "TIGERS";
        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.US_ASCII));
        readData(is);
    }

    private static void readData(InputStream is) throws IOException {

        System.out.print( (char) is.read()); // T
        is.skip(2); // Skip I and G
        is.read(); // Reads E but doesn't output it
        System.out.print( (char) is.read()); // R
        System.out.print( (char) is.read()); // S

    }
}
