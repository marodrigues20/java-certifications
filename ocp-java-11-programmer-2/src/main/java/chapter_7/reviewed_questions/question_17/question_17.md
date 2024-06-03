## What is the result of calling the following method?

```
3: public void addAndPrintItems(BlockingDeque<Integer> queue) {
4:    queue.offer(103);
5:    queue.offer(20, 1, TimeUnit.SECONDS);
6:    queue.offer(85, 8, TimeUnit.HOURS);
7:    System.out.print(queue.pool(200, TimeUnit.NANOSECONDS));
8:    System.out.print(" ", + queue.pool(1, TimeUnit.MINUTES));
9: }
```

A. It outputs 20 85
B. It outputs 103 20
C. It outputs 20 103
D. The code will not compiled
E. It compiles but throws an exception at runtime.
F. The outputs cannot be determined ahead of time.
G. None of the above.

Correct Answer: 

