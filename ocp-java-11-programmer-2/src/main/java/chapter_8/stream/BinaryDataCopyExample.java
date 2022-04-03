package chapter_8.stream;

import java.io.*;


/**
 * If the source file does not exist, a FileNotFoundException, which inherits IOException, will be thrown. If the
 * destination file already exists, this implementation will overwrite it, since the append flag was not sent. The
 * copy() method copies one byte at a time until it reads a value of -1.
 */
public class BinaryDataCopyExample {

    public static void main(String[] args) throws IOException {
        BinaryDataCopyExample binaryDataCopyExample = new BinaryDataCopyExample();
        binaryDataCopyExample.copyFile(new File(""), new File(""));
    }

    private void copyFile(File src, File dest) throws IOException {
        try (var in = new FileInputStream(src);
             var out = new FileOutputStream(dest)) {
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
        }
    }
}
