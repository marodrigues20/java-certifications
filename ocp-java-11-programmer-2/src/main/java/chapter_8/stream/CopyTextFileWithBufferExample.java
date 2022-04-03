package chapter_8.stream;

import java.io.*;

/**
 * We are checking for the end of stream with a null value instead of -1. Finally, we are inserting a newLine() on every
 * iteration of the loop. This is because readLine() strips out the line break character. Without the call to newLine(),
 * the copied file would have all of its line breaks removed.
 */
public class CopyTextFileWithBufferExample {

    public static void main(String[] args) {
        CopyTextFileWithBufferExample copyTextFileWithBufferExample = new CopyTextFileWithBufferExample();
        copyTextFileWithBufferExample.copyTextFileWithBuffer(new File(""), new File(""));
    }

    private void copyTextFileWithBuffer(File src, File dest) {
        try (var reader = new BufferedReader(new FileReader(src));
             var write = new BufferedWriter(new FileWriter(dest))) {
            String s;
            while ((s = reader.readLine()) != null) {
                write.write(s);
                write.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
