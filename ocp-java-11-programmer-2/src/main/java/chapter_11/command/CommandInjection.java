package chapter_11.command;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CommandInjection {


    public static void main(String[] args) {
        CommandInjection commandInjection = new CommandInjection();
        commandInjection.vunerableCode();
        //commandInjection.safeCode();
    }


    private void vunerableCode() {
        Console console = System.console();
        String dirName = console.readLine();
        Path path = Paths.get("c:/data/diets/" + dirName);
        try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(p -> p.toString().endsWith(".txt"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void safeCode() {
        Console console = System.console();
        String dirName = console.readLine();
        if (dirName.equals("mammal") || dirName.equals("birds")) {
            Path path = Paths.get("c:/data/diets/" + dirName); {
                try (Stream<Path> stream = Files.walk(path)) {
                    stream.filter(p -> p.toString().endsWith(".txt"))
                            .forEach(System.out::println);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
