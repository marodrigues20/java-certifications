package chapter_9.path.methods;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativizeExample {

    public static void main(String[] args) {
        //example1();
        //example2();
        //example3();
        example4();
    }

    private static void example4() {
        Path path3 = Paths.get("c:\\primate\\chimpanzee");
        Path path4 = Paths.get("d:\\storage\\bananas.txt");
        path3.relativize(path4);

    }

    private static void example3() {
        Path path1 = Paths.get("/primate/chimpanzee");
        Path path2 = Paths.get("bananas.txt");
        path1.relativize(path2);
    }

    private static void example2() {
        Path path3 = Paths.get("E:\\habitat");
        Path path4 = Paths.get("E:\\sanctuary\\raven\\poe.txt");
        System.out.println(path3.relativize(path4));
        System.out.println(path4.relativize(path3));
    }

    private static void example1() {
        var path1 = Path.of("fish.txt");
        var path2 = Path.of("friend/birds.txt");
        System.out.println(path1.relativize(path2));
        System.out.println(path2.relativize(path1));
    }
}
