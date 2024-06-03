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