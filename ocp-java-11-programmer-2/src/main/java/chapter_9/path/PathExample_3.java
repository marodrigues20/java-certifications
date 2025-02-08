package chapter_9.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample_3 {

    Path path1 = Paths.get("pandas/cuddly.png");
    Path path2 = Paths.get("c:\\zooinfo\\November\\employees.txt");
    Path path3 = Paths.get("/", "home", "zoodirectory");
}
