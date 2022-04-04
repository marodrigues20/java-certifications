package chapter_8.stream;

import java.io.*;

/**
 * The InputStreamReader class wraps an InputStream with a Reader, while the OutputStreamWriter class wraps an
 * OutputStream with a Writer.
 *
 * These classes are incredibly convenient and are also unique in that they are the only I/O stream classes to have
 * both InputStream / OutputStream and Reader / Writer in their name.
 */
public class ByteAndCharTogetherSample {

    public static void main(String[] args) throws IOException {
        try(Reader r = new InputStreamReader(System.in);
            Writer w = new OutputStreamWriter(System.out)){

        }
    }
}
