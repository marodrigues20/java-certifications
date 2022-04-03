package chapter_8.stream;

import java.io.*;

/**
 * Instead of reading the data one byte at a time, we read and write up to 1024 bytes at a time. The return value
 * lengthRead is critical for determining whether we are at the end of the stream and knowing how many bytes we
 * should write into our output stream.
 *
 */
public class CopyFileWithBufferExample {

    private void copyFileWithBuffer(File src, File dest) {
        try (var in = new BufferedInputStream(
                new FileInputStream(src));
             var out = new BufferedOutputStream(
                     new FileOutputStream(dest))) {
            var buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
