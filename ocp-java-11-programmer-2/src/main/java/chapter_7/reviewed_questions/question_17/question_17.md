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

Correct Answer: D

- The methods on line 5, 6, 7, and 8 each throw *InterruptedException*, which is a checked exception; therefore, the 
  method does not compile, and option D is the only correct answer.
- If *InterruptedException* was declared in the method signature on line 3, then the answer would be option F, because 
  adding items to the *queue* may be blocked at runtime.
- In this case, the queue is passed into the method, so there could be other threads operating on it.
- Finally, if the operations were not blocked and there were no other operations on the *queue* then the output would be
  103, 20, and the answer would be option B

