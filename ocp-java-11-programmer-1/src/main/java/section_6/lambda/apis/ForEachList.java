package section_6.lambda.apis;

import java.util.ArrayList;
import java.util.List;

public class ForEachList {

    public static void main(String[] args) {
        List<String> bunnies = new ArrayList<>();
        bunnies.add("long ear");
        bunnies.add("floppy");
        bunnies.add("hoppy");

        bunnies.forEach(b -> System.out.println(b));
        System.out.println(bunnies);
    }
}
