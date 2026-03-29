# OCP Java 11 Programmer I — Capítulo 6: Lambdas and Functional Interfaces

---

## 1. Writing Simple Lambdas

**Functional programming** is a way of writing code more declaratively — you specify what you want to do rather than dealing with the state of objects, focusing more on expressions than loops.

**Functional programming uses lambda expressions to write code.** A *lambda expression* is a block of code that gets passed around. It has parameters and a body just like full-fledged methods do, but it doesn't have a name like a real method. Lambda expressions are often referred to as *lambdas* for short, or closures in other languages.

A lambda expression is like a method that you can pass as if it were a variable. **Deferred execution** means that code is specified now but will run later — later being when the calling method invokes it.

---

### Lambda Example

The example below illustrates why lambdas are useful. Starting with the `Animal` class:

```java
public class Animal {
    private String species;
    private boolean canHop;
    private boolean canSwim;

    public Animal(String speciesName, boolean hopper, boolean swimmer) {
        species = speciesName;
        canHop = hopper;
        canSwim = swimmer;
    }

    public boolean canHop()  { return canHop; }
    public boolean canSwim() { return canSwim; }
    public String toString() { return species; }
}
```

An interface with one method to check a trait:

```java
public interface CheckTrait {
    boolean test(Animal a);
}
```

The traditional approach requires a separate class per check:

```java
public class CheckIfHopper implements CheckTrait {
    public boolean test(Animal a) {
        return a.canHop();
    }
}
```

And the full traditional search:

```java
1:  import java.util.*;
2:  public class TraditionalSearch {
3:      public static void main(String[] args) {
4:
5:          // list of animals
6:          List<Animal> animals = new ArrayList<Animal>();
7:          animals.add(new Animal("fish",     false, true));
8:          animals.add(new Animal("kangaroo", true,  false));
9:          animals.add(new Animal("rabbit",   true,  false));
10:         animals.add(new Animal("turtle",   false, true));
11:
12:         // pass class that does check
13:         print(animals, new CheckIfHopper());
14:     }
15:     private static void print(List<Animal> animals,
16:             CheckTrait checker) {
17:         for (Animal animal : animals) {
18:
19:             // the general check
20:             if (checker.test(animal))
21:                 System.out.print(animal + " ");
22:         }
23:         System.out.println();
24:     }
25: }
```

**With lambdas**, line 13 becomes a single expression — no extra class needed:

```java
// animals that can hop
print(animals, a -> a.canHop());

// animals that can swim
print(animals, a -> a.canSwim());

// animals that cannot swim
print(animals, a -> ! a.canSwim());
```

---

### Lambda Syntax

The simplest lambda expression:

```
a -> a.canHop()
```

Three parts (Figure 6.1 — short form):
- A single parameter specified with the name `a`
- The arrow operator `->` to separate parameter and body
- A body that calls a single method and returns its result

The most verbose (full) form (Figure 6.2):

```
(Animal a) -> { return a.canHop(); }
```

**Lambdas work with interfaces that have only one abstract method.** Java uses context to determine the parameter types and return type from the surrounding interface definition.

**Key syntax rules:**

| Situation | Rule |
|---|---|
| Single parameter, type not stated | Parentheses optional: `a -> ...` |
| Single parameter, type stated | Parentheses required: `(Animal a) -> ...` |
| Zero or two+ parameters | Parentheses always required: `() -> ...`, `(a, b) -> ...` |
| Single-statement body | Braces, `return`, and semicolon all optional |
| Multi-statement body | Braces, `return` (if needed), and semicolons required |
| `var` in parameter | Allowed in place of specific type: `(var a) -> ...` |

> Note: `s -> {}` is a valid lambda — if there is no code on the right side, you don't need a semicolon or `return`.

**Table 6.1 — Valid lambdas (returning boolean):**

| Lambda | # parameters |
|---|:---:|
| `() -> true` | 0 |
| `a -> a.startsWith("test")` | 1 |
| `(String a) -> a.startsWith("test")` | 1 |
| `(a, b) -> a.startsWith("test")` | 2 |
| `(String a, String b) -> a.startsWith("test")` | 2 |

> Note: there is no rule that says you must use all defined parameters.

**Table 6.2 — Invalid lambdas (that should return boolean):**

| Invalid lambda | Reason |
|---|---|
| `a, b -> a.startsWith("test")` | Missing parentheses |
| `a -> { a.startsWith("test"); }` | Missing `return` |
| `a -> { return a.startsWith("test") }` | Missing semicolon |

> The parentheses are optional *only* when there is one parameter and it doesn't have a type declared.

---

## 2. Introducing Functional Interfaces

**Lambdas work with interfaces that have only one abstract method.** These are called *functional interfaces*. This is officially known as the **Single Abstract Method (SAM)** rule.

> Note: Java provides the annotation `@FunctionalInterface` on some, but not all, functional interfaces. Having exactly one abstract method is what makes it a functional interface — not the annotation. The annotation just signals that the authors intend it to be safe for lambda use.

The four functional interfaces most likely to appear on the exam are: `Predicate`, `Consumer`, `Supplier`, and `Comparator`.

---

### Predicate

Located in `java.util.function`. Used to test a condition — returns `boolean`.

```java
public interface Predicate<T> {
    boolean test(T t);
}
```

Example — replaces the custom `CheckTrait` interface entirely:

```java
1:  import java.util.*;
2:  import java.util.function.*;
3:  public class PredicateSearch {
4:      public static void main(String[] args) {
5:          List<Animal> animals = new ArrayList<Animal>();
6:          animals.add(new Animal("fish", false, true));
7:
8:          print(animals, a -> a.canHop());
9:      }
10:     private static void print(List<Animal> animals,
11:             Predicate<Animal>  checker) {
12:         for (Animal animal : animals) {
13:             if (checker.test(animal))
14:                 System.out.print(animal + " ");
15:         }
16:         System.out.println();
17:     }
18: }
```

---

### Consumer

Located in `java.util.function`. Accepts a value and returns nothing.

```java
void accept(T t)
```

A common use case is printing a message:

```java
Consumer<String> consumer = x -> System.out.println(x);
```

Full example:

```java
public static void main(String[] args) {
    Consumer<String> consumer = x -> System.out.println(x);
    print(consumer, "Hello World");
}

private static void print(Consumer<String> consumer, String value) {
    consumer.accept(value);
}
```

This code prints `Hello World`. The `print()` method accepts a `Consumer` that knows how to print a value. When `accept()` is called, the lambda actually runs.

---

### Supplier

Located in `java.util.function`. Takes no parameters and returns a value.

```java
T get()
```

A good use case is generating values:

```java
Supplier<Integer> number = ()  ->  42;
Supplier<Integer> random = ()  ->  new Random().nextInt();
```

The first always returns `42`. The second generates a random number each time it is called.

Full example:

```java
public static void main(String[] args) {
    Supplier<Integer> number = () ->  42;
    System.out.println(returnNumber(number));
}

private static int returnNumber(Supplier<Integer> supplier) {
    return supplier.get();
}
```

When `returnNumber()` is called, it invokes the lambda to get the desired value — in this case, `42`.

---

### Comparator

Located in `java.util` (predates lambdas). Compares two values. Has many `static` and `default` methods.

```java
int compare(T o1, T o2)
```

Return value convention: negative = first value smaller, zero = equal, positive = first value bigger.

```java
// ascending order (natural sort)
Comparator<Integer> ints = (i1, i2) -> i1 - i2;

// descending order — two equivalent ways:
Comparator<String> strings    = (s1, s2) -> s2.compareTo(s1);  // backwards compareTo
Comparator<String> moreStrings = (s1, s2) -> - s1.compareTo(s2); // negated result
```

**Table 6.3 — Basic functional interfaces:**

| Functional interface | # parameters | Return type |
|---|:---:|---|
| `Comparator` | Two | `int` |
| `Consumer` | One | `void` |
| `Predicate` | One | `boolean` |
| `Supplier` | None | One (type varies) |

---

## 3. Working with Variables in Lambdas

Variables can appear in three places: the parameter list, local variables declared inside the lambda body, and variables referenced from the lambda body.

---

### Parameter List

Specifying the type of parameters is optional. Additionally, `var` can be used in place of the specific type. All three of these statements are interchangeable:

```java
Predicate<String> p = x -> true;
Predicate<String> p = (var x) -> true;
Predicate<String> p = (String x) -> true;
```

The type is inferred from context — for example, from what `Predicate` is parameterized with, or from the method signature where the lambda is passed.

Example where type is inferred from method signature:

```java
public void whatAmI() {
    consume((var x) -> System.out.print(x), 123);
}
public void consume(Consumer<Integer> c, int num) {
    c.accept(num);
}
```

Here `x` is `Integer` because `consume()` expects a `Consumer<Integer>`.

Example where type is inferred from the list being sorted:

```java
public void counts(List<Integer> list) {
    list.sort((var x, var y) -> x.compareTo(y));
}
```

---

### Local Variables inside the Lambda Body

A lambda body can be a block that contains local variable declarations:

```java
(a, b) -> { int c = 0; return 5; }
```

You **cannot** redeclare a variable that already exists in that scope:

```java
(a, b) -> { int a = 0; return 5; }  // DOES NOT COMPILE — 'a' already defined
```

Example with three syntax errors:

```java
11: public void variables(int a) {
12:     int b = 1;
13:     Predicate<Integer> p1 = a -> {    // ERROR 1: 'a' already used as method param
14:         int b = 0;                     // ERROR 2: redeclares local variable 'b'
15:         int c = 0;
16:         return b == c;                 // ERROR 3: missing semicolon before '}'
17: }
```

> Note: when writing your own code, a lambda block with a local variable is a good hint that you should extract that code into a method.

---

### Variables Referenced from the Lambda Body

Lambda bodies are allowed to reference some variables from the surrounding code:

```java
public class Crow {
    private String color;
    public void caw(String name) {
        String volume = "loudly";
        Consumer<String> consumer = s ->
            System.out.println(name + " says "
                + volume + " that she is " + color);
    }
}
```

This shows that a lambda can access an instance variable (`color`), a method parameter (`name`), and a local variable (`volume`).

**Rules:**

- **Instance variables** (and class/static variables) — always allowed
- **Method parameters** and **local variables** — allowed only if **effectively final**

**Effectively final** means the value of a variable doesn't change after it is set, regardless of whether it is explicitly marked as `final`. You can think of the above as if written:

```java
public void caw(final String name) {
    final String volume = "loudly";
    Consumer<String> consumer = s ->
        System.out.println(name + " says "
            + volume + " that she is " + color);
}
```

Example that does **not** compile because variables are not effectively final:

```java
2:  public class Crow {
3:      private String color;
4:      public void caw(String name) {
5:          String volume = "loudly";
6:          name = "Caty";        // name is no longer effectively final
7:          color = "black";      // instance variable — always OK
8:
9:          Consumer<String> consumer = s ->
10:             System.out.println(name + " says "   // ERROR on line 10
11:                 + volume + " that she is " + color);
12:         volume = "softly";   // volume is no longer effectively final — ERROR on line 11
13:     }
14: }
```

> Note: the compiler error for a non-effectively-final variable appears at the line where the **lambda tries to use it**, not where the reassignment happens.

**Table 6.4 — Rules for accessing a variable from a lambda body inside a method:**

| Variable type | Rule |
|---|---|
| Instance variable | Allowed |
| Static variable | Allowed |
| Local variable | Allowed if effectively final |
| Method parameter | Allowed if effectively final |
| Lambda parameter | Allowed |

---

## 4. Calling APIs with Lambdas

### `removeIf()`

`List` and `Set` declare a `removeIf()` method that takes a `Predicate`. It removes all elements that match the predicate.

```java
3: List<String> bunnies = new ArrayList<>();
4: bunnies.add("long ear");
5: bunnies.add("floppy");
6: bunnies.add("hoppy");
7: System.out.println(bunnies);  // [long ear, floppy, hoppy]
8: bunnies.removeIf(s -> s.charAt(0) != 'h');
9: System.out.println(bunnies);  // [hoppy]
```

Line 8 defines a predicate that takes a `String` and returns a `boolean`. The `removeIf()` method does the rest.

> Note: there is no `removeIf()` method on `Map` — it wouldn't be clear what was being removed (key or value).

---

### `sort()`

While you can call `Collections.sort(list)`, you can now sort directly on the list object using a `Comparator`:

```java
3: List<String> bunnies = new ArrayList<>();
4: bunnies.add("long ear");
5: bunnies.add("floppy");
6: bunnies.add("hoppy");
7: System.out.println(bunnies);   // [long ear, floppy, hoppy]
8: bunnies.sort((b1, b2) -> b1.compareTo(b2));
9: System.out.println(bunnies);   // [floppy, hoppy, long ear]
```

The `sort()` method takes a `Comparator` that provides the sort order. Remember: `Comparator` takes two parameters and returns an `int`.

> Note: there is no `sort()` method on `Set` or `Map` — neither of those types has indexing, so it wouldn't make sense to sort them.

---

### `forEach()`

`forEach()` takes a `Consumer` and calls that lambda for each element encountered.

**With List:**

```java
3: List<String> bunnies = new ArrayList<>();
4: bunnies.add("long ear");
5: bunnies.add("floppy");
6: bunnies.add("hoppy");
7:
8: bunnies.forEach(b -> System.out.println(b));
9: System.out.println(bunnies);
```

Output:
```
long ear
floppy
hoppy
[long ear, floppy, hoppy]
```

Line 8 prints one entry per line. Line 9 prints the entire list on one line.

**With Set** — works the same way as a List:

```java
Set<String> bunnies = Set.of("long ear", "floppy", "hoppy");
bunnies.forEach(b -> System.out.println(b));
```

**With Map** — you must choose whether to go through keys or values (both return a `Set`):

```java
Map<String, Integer> bunnies = new HashMap<>();
bunnies.put("long ear", 3);
bunnies.put("floppy", 8);
bunnies.put("hoppy", 1);
bunnies.keySet().forEach(b -> System.out.println(b));
bunnies.values().forEach(b -> System.out.println(b));
```

> Real World: Java has a functional interface called `BiConsumer` (not on this exam) that takes two parameters, which allows `forEach()` on a `Map` directly with key/value pairs:
> ```java
> bunnies.forEach((k, v) -> System.out.println(k + " " + v));
> ```

---

## 5. Summary

Lambda expressions, or lambdas, allow passing around blocks of code. The full syntax:

```java
(String a, String b) -> { return a.equals(b); }
```

The parameter types can be omitted. When only one parameter is specified without a type, the parentheses can also be omitted. The braces and `return` statement can be omitted for a single statement, making the short form:

```java
a -> a.equals(b)
```

Lambdas are passed to a method expecting an instance of a **functional interface** — one with a single abstract method (SAM rule).

- `Predicate` — returns `boolean`, takes any type
- `Consumer` — takes any type, doesn't return a value
- `Supplier` — returns a value, does not take any parameters
- `Comparator` — takes two parameters, returns an `int`

A lambda can define parameters or variables in the body as long as their names are different from existing local variables. The lambda body is allowed to use any instance or class variables. It can also use any local variables or method parameters that are **effectively final**.

Three common APIs that use lambdas: `removeIf()` on `List`/`Set` takes a `Predicate`; `sort()` on a `List` takes a `Comparator`; `forEach()` on `List`, `Set`, and `Map` takes a `Consumer`.

---

## 6. Exam Essentials

**Write simple lambda expressions.** Look for the presence or absence of optional elements in lambda code. Parameter types are optional. Braces and the `return` keyword are optional when the body is a single statement. Parentheses are optional when only one parameter is specified and the type is implicit.

**Identify common functional interfaces.** From a code snippet, identify whether the lambda is a `Comparator`, `Consumer`, `Predicate`, or `Supplier`. You can use the number of parameters and return type to tell them apart.

**Determine whether a variable can be used in a lambda body.** Local variables and method parameters must be effectively final to be referenced. This means the code must compile if you were to add the `final` keyword to these variables. Instance and class variables are always allowed.

**Use common APIs with lambdas.** Be able to read and write code using `forEach()`, `removeIf()`, and `sort()`.
