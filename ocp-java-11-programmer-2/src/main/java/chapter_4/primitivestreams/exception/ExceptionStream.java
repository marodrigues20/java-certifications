package chapter_4.primitivestreams.exception;

import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;

public class ExceptionStream {

    public void good() throws IOException{
        ExceptionCaseStudy.create().stream().count();
    }

    // Supplier doesn't allow checked exceptions
    public void bad() throws IOException{
        // Supplier<List<String>> s = ExceptionCaseStudy::create; DOES NOT COMPILER
    }

    //
    public void ugly(){
        Supplier<List<String>> s = () -> {
            try{
                return ExceptionCaseStudy.create();
            }catch (IOException e){
                throw new RuntimeException();
            }
        };
    }

    public void wrapped(){
        Supplier<List<String>> s2 =
                ExceptionCaseStudy::createSafe;
    }
}
