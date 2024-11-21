17. Assume *reader* is a valid stream that supports *mark()* and whose next characters are PEACOCKS.
    What is the expected output of the following code snippet?

```
    var sb = new StringBuilder();
    sb.append((char) reader.read());
    reader.mark(10);
    for(int i=0; i<2; i++){
        sb.append((char) reader.read());
        reader.skip(2);
    }
    reader.reset();
    reader.skip(0);
    sb.append((char) reader.read());
    System.out.println(sb.toString());
```

A. PEAE     <br>
B. PEOA     <br>
C. PEOE     <br>
D. PEOS     <br>
E. The code does not compile.   <br>
F. The code compiles but throws an exception at runtime.        <br>
G. The result cannot be determined with the information given.  <br>


Correct Answer: C

- The code compiles without issue.
- Since we're told the *Reader* supports *marks()*, the code also runs without throwing an exception.
- P is added to the *StringBuilder*, first.
- Next, the position in the stream is marked before E.
- The E is added to the *StringBuilder*, with AC being skipped, then the O is added to the *StringBuilder*, with CK 
  being skipped.
- The stream is then *reset()* to the characters to skip, so E is added onto the *StringBuilder* in the next *read()* 
  call. 
- The value PEOE is printed, and option C is correct.