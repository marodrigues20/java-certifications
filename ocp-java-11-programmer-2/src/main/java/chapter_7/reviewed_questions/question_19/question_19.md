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

A. It compiles and outputs the two numbers, followed by Printed.    <br>
B. The code will not compile because of line b1.                    <br>
C. The code will not compile because of line b2.                    <br>
D. The code will not compile because of line b3.                    <br>
E. The code will not compile because of line b4.                    <br>
F. It compiles, but the output cannot be determined ahead of time.  <br>
G. It compiles but throws an exception at runtime.                  <br>
H. It compiles but waits forever at runtime.                        <br>



Correct Answer: F, H.

- The application compiles and does not throw an exception, so options B, C, D, E, and G are incorrect.
- Even though the stream is processed in sequential order, the tasks are submitted to a thread executor, which may 
  complete the tasks in any order.
- Therefore, the output cannot be determined ahead of time, and option F is correct.
- Finally, the thread executor is never shut down; therefore, the code will run but it will never terminate, making 
  option H also correct.