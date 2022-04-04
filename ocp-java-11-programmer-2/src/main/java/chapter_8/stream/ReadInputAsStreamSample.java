package chapter_8.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * When executed, this application first fetches text from the user until the user presses the Enter key. It then
 * outputs the text the user entered to the screen.
 */
public class ReadInputAsStreamSample {

    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader( new InputStreamReader(System.in));
        String userInput = reader.readLine();
        System.out.println("You entered: " + userInput);

    }
}
