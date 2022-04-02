package chapter_8.stream;

import java.io.*;

public class ClosingWrappedStreamExample {

    public static void main(String[] args) {
        wrongWayToCloseWrappedStream();
        rightWayToCloseWrappedStream();
    }



    private static void wrongWayToCloseWrappedStream() {
        try(var fis = new FileOutputStream("zoo-banner.txt"); //Unnecessary
            var bis = new BufferedOutputStream(fis);
            var ois = new ObjectOutputStream(bis))
         {
             ois.writeObject("Object");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Instead, we can rely on the ObjectOutputStream to close the BufferedOutputStream and FileOutputStream.
     * The following will call only one close() method instead of three.
     */
    private static void rightWayToCloseWrappedStream() {
        try(var ois = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("zoo-banner.txt")
                ))) {
            ois.writeObject("Hello");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
