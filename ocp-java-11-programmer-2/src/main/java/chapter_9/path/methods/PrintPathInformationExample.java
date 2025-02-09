package chapter_9.path.methods;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PrintPathInformationExample {

    public static void main(String[] args) {
        PrintPathInformationExample printPathInformationExample = new PrintPathInformationExample();
        printPathInformation(Paths.get("zoo"));
        printPathInformation(Paths.get("/zoo/armadillo/shells.txt"));
        printPathInformation(Paths.get("./armadillo/../shells.txt"));
    }

    public static void printPathInformation(Path path) {
        System.out.println("Filename is: " + path.getFileName());
        System.out.println(" Root is: " + path.getRoot());
        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null) {
            System.out.println(" Current parent is: " + currentParent);
        }
    }
}
