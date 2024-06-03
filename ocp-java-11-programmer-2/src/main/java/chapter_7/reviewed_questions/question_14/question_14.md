## What statements about the following code snippet are true? (Choose all that apply.)

```java
import java.util.concurrent.Executors;

Object o1 = new Object();
Object o2 = new Object();
var service = Executors.newFixedThreadPool(2);
var f1 = service.submit(() -> {
    synchronized (o1) {
        synchronized (o2) {
            System.out.println("tortoise");
        }
    });

    var f2 = service.submit(() -> {
        synchronized (o2) {
            synchronized (o1) {
                System.out.println("Hare");
        }
    }
});
f1.get();
f2.get();
```

A. The code will always output *Torotoise* followed by Hare.
B. The code will always output *Hare* followed by *Tortoise*.
C. If the code does output anything, the order cannot be determined.
D. The code does not compile.
E. The code compiles but may produce a deadlock at runtime.
F. The code compiles but may produce a livelock at runtime.
G. It compiles but throws an exception at runtime.

## Correct answer: C; F