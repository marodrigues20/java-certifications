16. Assuming zoo-data.txt exists and is not empty, what statements about the following method are correct?
    (Choose all that apply.)

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

private void echo() throws IOException {
    var o = new FileWriter("new-zoo.txt");
    try (var f = new FileReader("zoo-data.txt");  
         var b = new BufferedReader(f); o){
        o.write(b.readLine());
    }
    o.write("");
}
```

Correct Answer: A; D;


A. When run, the method creates a new file with one line of text in it.
B. When run, the method creates a new file with two lines of text in it.
C. When run, the method creates a new file with the same number of lines as the original file.
D. The method compiles but will produce an exception at runtime.
E. The method does not compile.
F. The method uses byte stream classes.

- The method compiles, so option E is incorrect.
- The method creates a new-zoo.txt file and copies the first line from zoo-data.txt into it, making option A correct.
- The try-with-resources statement closes all of declared resources including the FileWrite o.
- For this reason, the Writer is closed when the last o.writer() is called, resulting in an IOException at runtime and making option D correct.
- Option F is incorrect because this implementation uses the character stream classes, which inherit from Reader or Writer.