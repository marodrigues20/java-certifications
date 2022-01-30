package chapter_4.primitivestreams.exception;

import java.io.IOException;
import java.util.List;

public class ExceptionCaseStudy {

    public static List<String> create() throws IOException{
        throw new IOException();
    }

    public static List<String> createSafe(){
        try{
            return ExceptionCaseStudy.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
