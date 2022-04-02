package chapter_8.stream;

import java.io.*;

/**
 * The following copyStream() methods show an example of reading all values of an InputStream and Reader
 * and writing them to an OutputStream and Writer, respectively. In both examples, -1 is used to indicate the end of
 * the stream.
 */
public class CopyStreamExample {


   void copyStream(InputStream in, OutputStream out) throws IOException {
        int b;
        while((b = in.read()) != -1){
            out.write(b);
        }
   }

   void copyStream(Reader in, Writer out) throws IOException {
        int b;
        while((b = in.read()) != -1 ){
            out.write(b);
        }
   }
}
