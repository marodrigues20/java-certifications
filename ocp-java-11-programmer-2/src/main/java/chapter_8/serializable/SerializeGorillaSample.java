package chapter_8.serializable;

import chapter_8.serializable.domain.Gorilla;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A sample method that serializes a List of Gorilla object to a file.
 */
public class SerializeGorillaSample {

    /**
     * The following code snippet shows how to call the serialization methods.
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var gorillas = new ArrayList<Gorilla>();
        gorillas.add(new Gorilla("Alex", 5, false));
        gorillas.add(new Gorilla("Ishmael", 8, true));
        File dataFile = new File("gorilla.data");

        SerializeGorillaSample sample = new SerializeGorillaSample();
        sample.saveToFile(gorillas, dataFile);

        var gorillasFromDisk = sample.readFromFile(dataFile);
        System.out.println(gorillasFromDisk);
    }

    /**
     * Serialize a List of Gorillas
     *
     * @param gorillas
     * @param dataFile
     * @throws IOException
     */
    private void saveToFile(List<Gorilla> gorillas, File dataFile) throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            for (Gorilla gorilla : gorillas) {
                out.writeObject(gorilla);
            }
        }
    }

    /**
     * When calling readObject, null and -1 do not have any special meaning, as someone might have serialized objects
     * with those values. Unlike our earlier techniques for reading methods from an input stream, we need to use an
     * infinite loop to process the data, which throws an EOFException when the end of the stream is reached.
     *
     * Tip: If your program happens to know the number of objects in the stream, then you can call readObject() a fixed
     * number of times, rather than using an infinite loop.
     *
     * Notice that readObject() declares a checked ClassNotFoundException since the class might not be available on
     * deserialization.
     *
     * Note: ObjectInputStream inherits an available() method from InputStream that you might think can be used to
     * check for the end of stream rather than throwing an EOFException. Unfortunately, this only tells you the number
     * of blocks that can be read without blocking another thread. In other words, it can return 0 even if there are
     * more bytes to be read.
     *
     * @param dataFile
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private List<Gorilla> readFromFile(File dataFile) throws IOException, ClassNotFoundException {

        var gorillas = new ArrayList<Gorilla>();

        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Gorilla)
                    gorillas.add((Gorilla) object);
            }
        } catch (EOFException e) {
            // File and reached
        }
        return gorillas;
    }
}
