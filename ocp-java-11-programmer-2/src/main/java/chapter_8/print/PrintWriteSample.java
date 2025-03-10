package chapter_8.print;

import java.io.*;

/**
 * You should pay close attention to the line breaks in the sample. For example, we called println() after our format(),
 * since format() does not automatically insert a line break after next text. One of the most common bugs with printing
 * data in practice is failing to account for line breaks properly.
 */
public class PrintWriteSample {

    public static void main(String[] args) throws IOException {
        File source = new File("zoo.log");
        try(var out = new PrintWriter(
                new BufferedWriter(new FileWriter(source)))){

            out.print("Today's weather is: ");
            out.println("Sunny");
            out.print("Today's temperature at the zoo is:");
            out.print(1 / 3.0);
            out.println('C');
            out.format("It has rained %5.2f inches this year %d", 10.2, 2021);
            out.println();
            out.printf("It may rain %s more inches this year", 1.2);
        }
    }
}
