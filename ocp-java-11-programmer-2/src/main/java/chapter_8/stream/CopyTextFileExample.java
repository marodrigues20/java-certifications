package chapter_8.stream;

import java.io.*;

public class CopyTextFileExample {

    public static void main(String[] args) {
        CopyTextFileExample copyTextFileExample = new CopyTextFileExample();
        copyTextFileExample.copyTextFile(new File(""),new File(""));
    }

    private void copyTextFile(File src, File dest){
        try(var reader = new FileReader(src);
            var writer = new FileWriter(dest)) {
            int b;
            while((b = reader.read()) != -1){
                writer.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
