package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DeepCopyExample {

    public static void main(String[] args) {

    }

    public void copyPath(Path source, Path target){

        try{
            Files.copy(source, target);
            if(Files.isDirectory(source))
                try(Stream<Path> s = Files.list(source)){
                    s.forEach(p -> copyPath(p,
                            target.resolve(p.getFileName())));
                }
        }catch (IOException e){
            //Handle exception
        }
    }
}

