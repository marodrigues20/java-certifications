package chapter_8.file;

import java.io.File;

/**
 * The following code creates a File object and determines whether the path it references exists within the file system:
 */
public class FileSeparator {

    public static void main(String[] args) {
        System.out.println(System.getProperty("file.separator"));
        System.out.println(File.separator);

        System.out.println(System.getProperties());
    }
}
