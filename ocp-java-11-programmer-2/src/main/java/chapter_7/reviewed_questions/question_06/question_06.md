## Which statement about the following code are correct? (Choose all that apply.)

```java
import java.util.List;

public static void main(String[] args) throws Exception {
    var data = List.of(2, 5, 1, 9, 8);
    data.stream().parallel()
            .mapToInt(s -> s)
            .peek(System.out::println)
            .forEachOrdered(System.out::println);
}
```

A. The peek() method will print the entries in the order: 1 2 5 8 9   <br>
B. The peek() method will print the entries in the order: 2 5 1 9 8   <br>
C. The peek() method will print the entries in an order that cannot be determined ahead of time. <br>
D. The forEachOrdered() method will print the entries in the order: 1 2 5 8 9.  <br>
E: The forEachOrdered() method will print the entries in an order that cannot be determined ahead of time. <br>
G: The code does not compile. <br>

## Correct Answeres: C; E

- C, E. The code compiles, so option G is incorrect.
- The peek() method on a parallel stream will process the elements concurrently, so the order cannot be determined ahead
  of a time, and option C is correct.
- The *forEachOrdered()* method will process the elements in the order they are stored in the stream, making option E correct.
- It does not sort the elements, so option D is incorrect.