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

A. The code will always output *Torotoise* followed by Hare. <br>
B. The code will always output *Hare* followed by *Tortoise*. <br>
C. If the code does output anything, the order cannot be determined. <br>
D. The code does not compile. <br>
E. The code compiles but may produce a deadlock at runtime. <br>
F. The code compiles but may produce a livelock at runtime. <br>
G. It compiles but throws an exception at runtime. <br>

## Correct answer: C; E

- The code compiles and runs without issues, so options C, D, E, and F are incorrect.
- The key here is that the order in which the resources o1 and o2 are synchronized could result in a deadlock.
- For example, if the first thread gets a lock on o1 and second thread gets a lock on o2 before either thread can get 
  there second lock, then the code will hang at runtime, making option E correct.
- The code cannot produce a livelock, since both threads are waiting, so option F is incorrect.
- Finally, if a deadlock does occur, an exception will not be thrown, so option G is incorrect.