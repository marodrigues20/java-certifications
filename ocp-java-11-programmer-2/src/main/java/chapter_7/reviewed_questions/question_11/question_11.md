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

A. The first value printed is always 1. <br>
B. The second value printed is always 6. <br>
C. The code will not compile because of line y1. <br>
D. The code will not compile because of line y2. <br>
E. The code compiles but throws an exception at runtime. <br>
F. The output cannot be determined ahead of time. <br>
G. It compiles but waits forever at runtime. <br>


## Correct Answer: F

- The code compiles and runs without issue, so options C, D, E, and G are incorrect.
- There are two important things to notice.
- First, synchronizing on the first variable doesn't actually impact the result of the code.
- Second, sorting on a parallel stream does not mean that *findAny()*  will return the first record.
- The *findAny()* method will return the value from the first thread that retrieves a record.
- Therefore, the output is not guaranteed, and option F is correct.
- Option A looks correct, but even on serial streams, *findAny()* is free to select any element.

