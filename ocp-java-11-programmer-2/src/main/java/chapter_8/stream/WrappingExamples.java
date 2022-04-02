package chapter_8.stream;

import java.io.*;

/**
 * A high-level stream is built on top of another stream using wrapping. Wrapping is the process by which
 * an instance is passed to the constructor of another class, and operations on the resulting instance are filtered and
 * applied to the original instance.
 */
public class WrappingExamples {

    public static void main(String[] args) {
        example1();
        example2();
    }

    /**
     * Character Stream
     */
    private static void example1() {
        try (var br = new BufferedReader(new FileReader("zoo-data.txt"))) {

            System.out.println(br.readLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Byte Stream
     */
    private static void example2() {

        try (var ois = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream("zoo-data.txt")
                )
        )) {
            System.out.println(ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
