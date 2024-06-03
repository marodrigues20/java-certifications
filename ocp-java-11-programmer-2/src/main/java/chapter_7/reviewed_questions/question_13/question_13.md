## What statements about the following code are true? (Choose all that apply.)

```java
import java.util.List;

private void test() {
    System.out.print(List.of("duck","flamingo","pelican")
            .parallelStream().parallel() // q1
            .reduce(0,
                    (c1,c2) -> c1.length() + c2.length(),  // q2
                    (s1,s2) -> s1 + s2 )); // q3     
}
```

A. It compiles and runs without issue, outputting the total length of all string in the stream.
B. The code will not compile because of line q1.
C. The code will not compile because of line q2.
D. The code will not compile because of line q3.
E. It compiles but throws an exception at runtime.
F. None of the above

## Correct Answers: B