package chapter_9.path;

import java.nio.file.Path;

public class PathExample_2 {

    public static void main(String[] args) {
        Path path1 = Path.of("pandas", "cuddly.png");
        Path path2 = Path.of("c:", "zooinfo", "November", "employees.txt");
        Path path3 = Path.of("/", "home", "zoodirectory");
    }
}
