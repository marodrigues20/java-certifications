## What statement about the following code is true?

```java
public static void main(String[] args) {
    var value1 = new AtomicLong(0);
    final long[] value2 = {0};
    IntStream.iterate(1, i -> 1).limit(100).parallel()
            .forEach(i -> value1.incrementAndGet());
    IntStream.iterate(1, i -> 1).limit(100).parallel()
            .forEach(i -> ++value2[0]);
    System.out.println(value1 + " " + value2[0]);
}
```

A. It outputs 100 100.
B. It outputs 100 99
C. The output cannot be determined ahead of time.
D. The code does not compile.
E. It compiles but throws an exception at runtime.
F. It compiles but enters an infinite loop at runtime.
G. None of the above


## Correct Answer: E