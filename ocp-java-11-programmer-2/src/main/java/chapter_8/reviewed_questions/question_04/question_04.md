4. Which classes will allow the following to compile? (Choose all that apply.)

```
var is = new BufferedInputStream(new FileInputStream("z.txt"));
InputStream wrapper = new ________________ (is);
try (wrapper) {}
```

A. BufferedInputStream  <br>
B. FileInputStream      <br>
C. BufferedWriter       <br>
D. ObjectInputStream    <br>
E. ObjectOutputStream   <br>
F. BufferedReader       <br>
G. None of the above, as the first line does not compile. <br>

- Correct Answer: A; D


- The code will compile if the correct classes are used, so option G is incorrect.
- Remember, a try-with resources statement can use resources declared before the start of the statement.
- The reference type of *wraper* is *InputStream*, so we need a class that inherits *InputStream*.
- We can eliminate *BufferedWriter*, *ObjectOutputStream*, and *BufferedReader* since their names do not end in *InputStream*
- Next, we see the class must take another stream as input, so we need to choose the remaining stream that are high-level
  streams. *BufferedInputStream* is a high-level stream, so option A is correct.
- Even though the instance is already a *BufferedInputStream*, there's no rule that it can't be wrapped multiple times by
  a high-level stream.
- Finally, option D is correct - an *ObjectInputStream* is a high-level stream that operates on other streams.

