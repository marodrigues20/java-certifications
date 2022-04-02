package chapter_8.file;

import java.io.File;

public class FileOutputsExample {

    public static void main(String[] args) {
        var file = new File("/Users/ma20338551");
        System.out.println("File Exists:" + file.exists());
        if (file.exists()) {
            System.out.println("Absolute Path: " + file.getAbsolutePath());
            System.out.println("Is Directory: " + file.isDirectory());
            System.out.println("Parent Path: " + file.getParent());
            if (file.isFile()) {
                System.out.println("Size: " + file.length());
                System.out.println("Last Modified: " + file.lastModified());
            } else {
                for (File subfile : file.listFiles()) {
                    System.out.println(" " + subfile.getName());
                }
            }

        }

    }
}
