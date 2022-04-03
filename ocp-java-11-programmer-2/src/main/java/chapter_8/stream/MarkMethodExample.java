package chapter_8.stream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Assume that we have an InputStream instance whose next value are LION. Consider the following code snipped:
 *
 * The code snippet will output LIOION if mark() is supported, and LION otherwise. It's a good practice to organize
 * your read() operations so that the stream ends up at the same position regardless of the mark is supported.
 *
 * What about the value of 100 we passed to the mark() method? This value is called the readLimit. It instructs the
 * stream that we expect to call reset() after at most 100 bytes. If our program calls reset() after reading more
 * than 100 bytes from calling mark(100), then it may throw an exception, depending on the stream class.
 *
 * Tip: In actually, mark() and reset are not really putting the data back into the stream but storing the data in a
 * temporary buffer in memory to be read again. Therefore, you should not call the mark() operation with too large
 * a value, as this could take up a lot of memory.
 */
public class MarkMethodExample {

    public static void main(String[] args) throws IOException {

        String str = "LION";
        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.US_ASCII));
        readData(is);
    }

    private static void readData(InputStream is) throws IOException {
        System.out.print((char) is.read()); //L
        if(is.markSupported()){
            is.mark(100); //Marks up to 100 bytes
            System.out.print((char) is.read()); // I
            System.out.print((char) is.read()); // O
            is.reset(); //Resets stream to position before I
        }
        System.out.print((char) is.read()); // I
        System.out.print((char) is.read()); // O
        System.out.print((char) is.read()); // N
    }


}
