## What statements about the following code are true? (Choose all that apply.)

```java

    Integer i1 = List.of(1, 2, 3, 4, 5).stream().findAny().get();
    synchronized (i1) { // y1
        Integer i2 = List.of(6, 7, 8, 9, 10)
                .parallelStream()
                .sorted()
                .findAny().get(); // y2
        System.out.println(i1 + " " + i2);
    }

```

A. The first value printed is always 1.
B. The second value printed is always 6.
C. The code will not compile because of line y1.
D. The code will not compile because of line y2.
E. The code compiles but throws an exception at runtime.
F. The output cannot be determined ahead of time.
G. It compiles but waits forever at runtime.


## Correct Answer: F