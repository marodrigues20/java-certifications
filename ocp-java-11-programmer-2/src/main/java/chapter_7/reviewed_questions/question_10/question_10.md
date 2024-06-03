## What is the result of executing the following code snippet?

```java
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;

private void test() {

    List<Integer> lions = new ArrayList<>(List.of(1, 2, 3));
    List<Integer> tigers = new CopyOnWriteArrayList<>(lions);
    Set<Integer> bears = new ConcurrentSkipListSet<>();
    bears.addAll(lions);
    for(Integer item: tigers) tigers.add(4); // x1
    for(Integer item: bears) bears.add(5); // x2
    System.out.println(lions.size() + " " + tigers.size()
        + " " + bears.size());
}
```

A. It outputs 3 6 4.
B. It outputs 6 6 6.
C. It outputs 6 3 4.
D. The code does not compile.
E. It compiles but throws an exception at runtime on line x1.
F. It compiles but throws an exception on line x2.
G. It compiles but enters an infinite loop at runtime.


## Correct Answers: D