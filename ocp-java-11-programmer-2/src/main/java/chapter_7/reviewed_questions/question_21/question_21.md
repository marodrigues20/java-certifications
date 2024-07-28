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

Correct Answer: A, B, D