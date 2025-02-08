package chapter_9.path;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample_4 {

    public static void main(String[] args) throws URISyntaxException {

        URI a = new URI("file://icecream.txt");
        Path b = Path.of(a);
        Path c = Paths.get(a);
        URI d = b.toUri();
    }
}
