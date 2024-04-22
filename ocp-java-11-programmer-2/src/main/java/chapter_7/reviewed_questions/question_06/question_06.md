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

A. The peek() method will print the entries in the order: 1 2 5 8 9
B. The peek() method will print the entries in the order: 2 5 1 9 8
C. The peek() method will print the entries in an order that cannot be determined ahead of time.
D. The forEachOrdered() method will print the entries in the order: 1 2 5 8 9.
E: The forEachOrdered() method will print the entries in an order that cannot be determined ahead of time.
G: The code does not compile.

## Correct Answeres: C; E