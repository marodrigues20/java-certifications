## Given the following code snippet and black lines on p1 and p2, which values guarantee 1 
## is printed at runtime? (Choose all that apply.)

```
var data = List.of(List.of(1, 2),
  List.of(3, 4),
  List.of(5, 6));
data._________________ // p1
    .flatMap(s -> s.stream())
    ._____________ // p2
    .ifPresent(System.out::println);
```

- A. stream() on line p1, findFirst() on line p2.
- B. stream() on line p1, findAny() on line p2.
- C. parallelStream() on line p1, findFirst() on line p2.
- D. parallelStream() on line p1, findAny() on line p2.
- E. The code does not compile regardless of what is inserted into the blank.
- F. None of the above. 

Correct Answer: A, D.


- The *findFirst()* method guarantees the first element in the stream will be returned, whether it is serial or parallel,
  making options A and D correct.
- While option B may consistently print 1 at runtime, the behavior of *findAny()* on a serial stream is not guaranteed,
  so option B is incorrect.
- Option C is likewise incorrect, with the output being random at runtime.
- Option E is incorrect because any of the previous options will allow the code to compile.