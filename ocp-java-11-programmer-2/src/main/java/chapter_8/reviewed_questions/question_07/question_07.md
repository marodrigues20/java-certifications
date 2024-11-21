7. Assume that *in* is a valid stream whose next bytes are XYZABC. What is the result of calling the <br>
   following method on the stream, using a *count* value of 3?

```java
public static String pullBytes(InputStream in, int count) throws IOException{
    in.mark(count);
    var sb = new StringBuilder();
    for(int i=0; i < count; i++)
        sb.append((char) in.read());
    in.reset();
    in.skip(1);
    sb.append((char) in.read());
    return sb.toString();
}
```


A. It will return a *String* value of XYZ.                      <br>
B. It will return a *String* value of XYZA.                     <br>
C. It will return a *String* value of XYZX.                     <br>
D. It will return a *String* value of XYZY.                     <br>
E. The code does not compile.                                   <br>
F. The code compiles but throws an exception at runtime.        <br>
G. The result cannot be determined with the information give.   <br>

- Correct Answer: G

- Not all I/O streams support the *mark()* operation; 
- Therefore, without calling *markSupported()* on the stream, the result is unknown until runtime.
- If the stream does support the *mark()* operation, then the result would be XYZY, and option D would be correct.
- The *reset()* operation puts the stream back in the position before the *mark()* was called, and *skip(1)* will skip X.
- If the stream does not support the *mark()* operation, a runtime exception would likely be thorwn, and option F would 
  be correct.
- Since you don't know if the input stream supports the *mark()* operation, option G is the only correct choice.
