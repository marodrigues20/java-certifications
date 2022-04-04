package chapter_8.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * If you are running from a command prompt, they will likely print text in the same format. On the other hand,
 * if you are working in an integrated development environment (IDE), they might print  the System.err  text in a different
 * color. Finally, if the code is being run on another server, the System.err stream might write to a different log file.
 */
public class SystemPrintExample {

    public static void main(String[] args) throws IOException {
        try(var in = new FileInputStream("zoo.txt")){
            System.out.println("Found file!");
        }catch (FileNotFoundException e){
            System.err.println("File not Found!");
        }
    }
}
