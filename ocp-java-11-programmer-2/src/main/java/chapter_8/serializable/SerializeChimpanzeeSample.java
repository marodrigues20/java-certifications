package chapter_8.serializable;

import chapter_8.serializable.domain.Chimpanzee;
import chapter_8.serializable.domain.Gorilla;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A sample method that serializes a List of Chimpanzee object to a file.
 *
 * Explanation:  Well, for starters, none of the instance members would be serializable to a file. The name and age
 * variables are both market transient, while the type variable are both market transient, while the type variable
 * is static. We purposely accessed the type variable using this to see whether you were paying attention.
 * Upon deserialization, none of the constructor in Chimpanzee is called. Even the no-arg constructor that sets the
 * value [ name=Unknown, age=12, type=Q ] is ignored. The instance initializer that sets age to 14 is also not executed.
 *
 * In this case, the name variable is initialized to null since that's the default value for String in Java.
 * Likewise, the age variable is initialized to 0. The program prints the following, assuming the toString() methods is
 * implemented.
 *
 * [Chimpanzee{name='null', age=0, type=B},
 *  Chimpanzee{name='null', age=0, type=B}]
 *
 *  What about the type variable? Since it's static, it will actually display whatever value was set last. If the data
 *  is serialized and deserialized within the same execution, then it will display B, since that was the last
 *  Chimpanzee we created. On the other hand, if the program performs the deserialization and print  on startup, then
 *  it will be C, since that is the value the class is initialized with.
 */
public class SerializeChimpanzeeSample {

    /**
     * The following code snippet shows how to call the serialization methods.
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var chimpanzee = new ArrayList<Chimpanzee>();
        chimpanzee.add(new Chimpanzee("Ham", 2, 'A'));
        chimpanzee.add(new Chimpanzee("Enos", 4, 'B'));
        File dataFile = new File("chimpanzee.data");

        SerializeChimpanzeeSample sample = new SerializeChimpanzeeSample();
        sample.saveToFile(chimpanzee, dataFile);

        var chimpanzeesFromDisk = sample.readFromFile(dataFile);
        System.out.println(chimpanzeesFromDisk);
    }

    /**
     * Serialize a List of Chimpanzees
     *
     * @param chimpanzees
     * @param dataFile
     * @throws IOException
     */
    private void saveToFile(List<Chimpanzee> chimpanzees, File dataFile) throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            for (Chimpanzee chimpanzee : chimpanzees) {
                out.writeObject(chimpanzee);
            }
        }
    }


    private List<Chimpanzee> readFromFile(File dataFile) throws IOException, ClassNotFoundException {

        var chimpanzees = new ArrayList<Chimpanzee>();

        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Chimpanzee)
                    chimpanzees.add((Chimpanzee) object);
            }
        } catch (EOFException e) {
            // File and reached
        }
        return chimpanzees;
    }
}
