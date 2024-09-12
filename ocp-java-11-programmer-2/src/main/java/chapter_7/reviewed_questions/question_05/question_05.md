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

A. It outputs 100 100. <br>
B. It outputs 100 99 <br>
C. The output cannot be determined ahead of time. <br>
D. The code does not compile. <br>
E. It compiles but throws an exception at runtime. <br>
F. It compiles but enters an infinite loop at runtime. <br>
G. None of the above. <br>


## Correct Answer: C

- C. The code compiles and runs without throwing an exception or entering an infinite loop, so options D, E and F are incorrect.
- The key here is that the increment operator ++ is not atomic.
- While the part of the output will always be 100, the second part is nondeterministic.
- It could output any value from 1 to 100, because the threads can overwrite each other's work, because the threads can
- overwrite each other's work.
- Therefore, option C is the correct answer, and options A and B are incorrect.