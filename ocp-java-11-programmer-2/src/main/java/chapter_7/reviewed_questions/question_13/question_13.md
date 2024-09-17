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

A. It compiles and runs without issue, outputting the total length of all string in the stream. <br>
B. The code will not compile because of line q1. <br>
C. The code will not compile because of line q2. <br>
D. The code will not compile because of line q3. <br>
E. It compiles but throws an exception at runtime. <br>
F. None of the above. <br>

## Correct Answers: C

- The code does not compile, so options A and E are incorrect.
- The problem here is that *c1* is an *int* and *c2* is a *String*, so the code fails to combine on line *q2*, since 
  calling *lengh()* on an *int* is not allowed, and option C is correct.
- The rest of the lines compile without issue.
- Note that calling *parallel()* on an alrady parallel stream is allowed, and it may in fact return the same objective.