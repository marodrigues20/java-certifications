package chapter_8.file;

import java.io.File;

/**
 * There are three File constructor you should know for the exam.
 *
 * If the parent instance is null, then it would be skipped, and the method would revert to the single String
 * constructor.
 */
public class FileConstructors {

    public static void main(String[] args) {

        File file = new File("/Users");

        File file1 = new File(file, "/ma20338551");

        File file2 = new File("/Users", "/ma20338551");


    }
}
