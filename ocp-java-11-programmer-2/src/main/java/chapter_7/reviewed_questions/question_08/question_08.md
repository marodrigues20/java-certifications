## Assuming this class is accessed by only a single thread at a time, what is the result of calling the 
## *countIceCreamFlavors()* method?

```java

import java.util.stream.LongStream;

public class Flavors {
    private static int counter;
    public static void countIceCreamFlavors() {
        counter = 0;
        Runnable task = () -> counter++;
        LongStream.range(1, 500)
                .forEach(m -> new Thread(task).run());
        System.out.println(counter);
    }
}
```

A. The method consistently prints 499.
B. The method consistently prints 500.
C. The method compiles and prints a value, but that value cannot be determined ahead of time.
D. The method does not compile.
E. The method compiles but throws an exception at runtime.
F. None of the above.

## Correct Answer: A


- The method looks like executes a task concurrently, but it actually runs synchronously.
- In each iteraction of the *forEach()* loop, the process waits for the *run()* method to complete before moving on.
- For this reason, the code is actually thread-safe.
- It executes a total of 499 times, since the second value of *range()* excludes the 500.
- Since the program consistently prints 499 at runtime, option A is correct.
- Note that if *starts()* had been used instead of *run()* (or the stream was parallel), then the output would be 
  indeterminate, option C would have been correct.