## What is the result of executing the following application? (Choose all that apply.)

```java
import java.util.concurrent.Executors;

public class PrintConstraints {
    public static void main(String[] args) {
        var s = Executors.newScheduledThreadPool(10);
        DoubleStream.of(3.14159,2.71828)  // b1
                .forEach(c -> s.submit( // b2
                        () -> System.out.println(10*c))); // b3
        s.execute(() -> System.out.println("Printed"));  // b4
    }
}
```

A. It compiles and outputs the two numbers, followed by Printed.
B. The code will not compile because of line b1.
C. The code will not compile because of line b2.
D. The code will not compile because of line b3.
E. The code will not compile because of line b4.
F. It compiles, but the output cannot be determined ahead of time.
G. It compiles but throws an exception at runtime.
H. It compiles but waits forever at runtime.



Correct Answer: C