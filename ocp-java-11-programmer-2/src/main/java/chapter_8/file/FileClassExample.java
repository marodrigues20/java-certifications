package chapter_8.file;

import java.io.File;

/**
 * The following code creates a File object and determines whether the path it references exists within the file system:
 *
 * When working with an instance of the File class, keep in mind that it only represents a path to a file. Unless operated
 * upon, it is not connected to an actual file within the file system.
 */
public class FileClassExample {

    public static void main(String[] args) {
        var zooFile1 = new File("/home/tiger/data/stripes.txt");
        System.out.println(zooFile1.exists());
    }
}
