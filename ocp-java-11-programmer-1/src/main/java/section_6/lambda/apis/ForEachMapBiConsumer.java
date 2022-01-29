package section_6.lambda.apis;

import java.util.HashMap;
import java.util.Map;

public class ForEachMapBiConsumer {

    public static void main(String[] args) {
        Map<String, Integer> bunnies = new HashMap<>();
        bunnies.put("long ear", 3);
        bunnies.put("floppy", 8);
        bunnies.put("hoppy", 1);
        bunnies.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
