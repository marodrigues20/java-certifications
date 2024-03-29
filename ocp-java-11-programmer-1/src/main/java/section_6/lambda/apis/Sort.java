package section_6.lambda.apis;

import java.util.ArrayList;
import java.util.List;

public class Sort {

    public static void main(String[] args) {
        List<String> bunnies = new ArrayList<>();
        bunnies.add("long ear");
        bunnies.add("floppy");
        bunnies.add("hoppy");
        System.out.println(bunnies); // [long ear, floppy, hoppy]
        bunnies.sort((b1, b2) -> b1.compareTo(b2));
        System.out.println(bunnies); // [floppy, hoppy, long ear]
    }
}
