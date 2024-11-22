19. Given the following method, which statement are correct? (Choose all that apply.)

```java
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public void copyFile(File file1, File file2) throws Exception {
    var reader = new InputStreamReader(new FileInputStream(file1));
    try (var writer = new FileWriter(file2)){
        char[] buffer = new char[10];
        while(reader.read(buffer) != -1){
            writer.write(buffer);
            // n1
        }
    }
}
```

Correct Answer: C; E; G

A. The code does not compile because *reader* is not a *Buffered* stream. <br>
B. The code does not compile because *writer* is not a *Buffered* stream. <br>
C. The code compiles and correctly copies the data between some files.<br>
D. The code compiles and correctly copies the data between all files. <br>
E. If we check *file2* on line *n1* within the file system after five iterations of the *while* loop, it may be empty. <br>
F. If we check *file2* on line *n1* within the file system after five iterations, it will contain exactly 50 characters. <br>
G. This method contains a resource leak. <br>


- First, the method does compile, so options A and B are incorrect.
- Methods to read/write *byte[]* values exist in the abstract parent of all I/O stream classes.
- This implementation is not correct, though, as the return value of *read(buffer)* is not used properly.
- It will only correctly copy files whose character count is a multiple of *10*, making option C correct and option 
  D incorrect.
- Option E is also correct as the data may not have made it to disk yet.
- Option F would be correct if the *flush()* method was called after every write.
- Finally, option G is correct as the *reader* stream is never closed.