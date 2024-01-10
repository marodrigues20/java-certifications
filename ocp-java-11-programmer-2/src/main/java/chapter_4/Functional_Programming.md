# Chapter 4 - Functional Programming

- Java Stream API
  - Describe the Stream interface and pipelines
  - Use lambda expressions and method references
- Built-in Functional interfaces
  - Use interfaces from the java.util.function package
  - Use core functional interfaces including Predicate, Consumer, Function and Supplier
  - Use primitive and binary variations of base interfaces of java.util.function package.
- Lambda Operations on Streams
  - Extract stream data using map, peek and flatMap methods
  - Search stream data using search findFirst, findAny, anyMatch, allMatch and noneMatch methods
  - Use the Optional class
  - Perform calculations using count, max, min, average and sum stream operations
  - Sort a collection using lambda expression
  - Use Collectors with stream, including the groupingBy and partitioningBy operations

Note that the Stream API in this chapter is used for functional programming. By contrast, there are also java.io streams,
which we will talk about in Chapter8, "I/O". Despite both using the word stream, they are nothing alike.

## Working with Built-in Functional Interfaces


- All of the functional interfaces in Table 4.1 are provided in the java.util.function package.
- The convention here is to use the generic type T for the type parameter. If a second type parameter is needed, the 
next letter, U, is used. If a distinct return type is needed, R for return is used for the generic type.

- TABLE 4.1 


| Functional Interfaces | Return type | Method name  | # parameters |
|-----------------------|-------------|--------------|--------------|
| Supplier< T >         | T           | get()        | 0            |
| Consumer< T >         | void        | accept(T)    | 1(T)         |
| BiConsumer<T, U>      | void        | accept(T, U) | 2(T,U)       |
| Predicate< T >        | boolean     | test(T)      | 1(T)         |
| BiPredicate<T, U>     | boolean     | test(T, U)   | 2(T,U)       |
| Function<T, R>        | R           | apply(T)     | 1(T)         |
| BiFunctinal<T, U, R>  | R           | apply(T, U)  | 2(T,U)       |
| UnaryOperator<T>      | T           | apply(T)     | 1(T)         |
| BinaryOperator<T>     | T           | apply(T,T)   | 2 (T, T)     |


- Note 1: Table 4.1 is a subset of what you need to know. Many functional interfaces are defined in the java.util.function
package. There are even functional interfaces for handling primitives, which you'll see later in the chapter.
- Note 2: Table 4.1 is significant because these interfaces are often used in streams and other classes that come with 
Java, which is why you need to memorize them for the exam.
- Note 3: Chapter 7, there are 2 more functional interfaces called Runnable and Callable.

## Implementing Supplier

- A Supplier is used when you want to generate or supply values without talking any input. The Supplier interface is 
defined as follows:

```
@FunctionalInterface
public interface Supplier<T>{
  T get();
}
```

- You can create a LocalDate object using the factory method now(). This example shows how to use a Supplier to call 
this factory:

i.e: chapter_4.funcional.supplier.SupplierExamples.java

```
  Supplier<LocalDate> s1 = LocalDate::now;
  Supplier<LocalDate> s2 = () -> LocalDate.now();
  
  LocalDate d1 = s1.get();
  LocalDate d2 = s2.get();
  
  System.out.println(d1);  // Printout 2020-02-20
  System.out.println(d1);  // Printout 2020-02-20
```


- It's also a good opportunity to review static method references. The LocalDate::now method reference is used to create
a Supplier to assign to an intermediate variable s1.
- A Supplier is often used when constructing new objects.

i.e: i.e: chapter_4.funcional.supplier.SupplierExamples.java


## Implementing Consumer and BiConsumer

- You use a Consumer when you want to do something  with a parameter but not return anything. BiConsumer does the same
thing except that it takes two parameters. The interfaces are defined as follows:

```
@FunctionalInterface
public interface Consumer<T>{
  void accept(T t);
  // ommited defautl method
}

@FunctionalInterface
public interface BiConsumer<T, U>{
  void accept(T t, U u>);
  // omitted default method
}
```

- It should be old news by now that you can use a Predicate to test a condition.

```
  Predicate<String> p1 = String::isEmpty;
  Predicate<String> p2 = x -> x.isEmpty();
  
  System.out.println(p1.test(""));  //true
  System.out.println(p2.test("")); //true
```

i.e: chapter_4.funcional.predicate.SupplierExamples.java

- More interesting is a BiPredicate. This example also prints true twice.

```
  BiPredicate<String, String> b1 = String::startsWith;
  BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
  
  System.out.println(b1.test("chicken", "chick")); //true
  System.out.println(b2.test("chicken", "chick"));
```

i.e: chapter_4.funcional.bipredicate.BiPredicateExample.java

## Implementing Function and BiFunction

- In Chapter 3, we used Function with merge() method. A Function is responsible for turning one parameter into a value 
  of a potentially different type and returning it. Similarly, a BiFunction is responsible for turning two parameters
  into a value and returning it. The interfaces are defined as follows:

```java
@FunctionalInterface
public interface Function<T, R>{
    R apply(T t);
    // omitted default and static methods
}

@FunctionalInterface
public interface BiFunction<T, U, R>{
    R apply(T t, U u);
}
```

- For example, this function converts a String to the length of the String

i.e: chapter_4.funcional.function.FunctionExample.java

```
    Function<String, Integer> f1 = String::length;
    Function<String, Integer> f2 = x -> x.length;
    
    System.out.println(f1.apply("cluck")); //5
    System.out.println(f2.apply("click")); //5
```

- This function turns a String into a Integer. Well, technically it turns the String into an int, which is autoboxed 
into an Integer. The types don't have to be different combines two String objects and produce another String:


i.e: chapter_4.funcional.bifunction.BiFunctionExample.java
```

  BiFunction<String, String, String> b1 = String::concat;
  BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);

  System.out.println(b1.apply("baby ", "chick")); // baby chick
  System.out.println(b2.apply("baby ", "chick")); // baby chick

```

- The first two types in the BiFunction are the input types. The third is the result type. For the method reference,
  the first parameter is the instance the concat() is called on, and the second is passed to concat().

## Creating Your Own Functional Interfaces

- Java provides a built-in interface for functions with one or two parameters. What if you need more?
- Suppose that you want to create a functional interface for the wheel speed of each wheel on a tricycle. 
- You could create a functional interface such as this:

```java
@FunctionalInterface
interface TriFunction<T,U,V,R>{
    R apply(T t, U r, V v);
}
```

- There are four type parameters. The first three supply the types of the three wheel speeds. The fourth is the return 
  type. Now suppose that you want to create a function to determine how fast your quad-copter is going given the power 
  of the four motors. You could create a functional interface such as the following:

```java
@FunctionalInterface
interface QuadFunction<T,U,V,W,R>{
    R apply(T t, U u,V v,W w);
}
```

- Remember that you can add any functional interfaces you'd like, and Java matches them when you use lambdas or method 
  references.


## Implementing UnaryOperator and BinaryOperator

- UnaryOperator and BinaryOperator are a special case of a Function. They require all type parameters to be the same type.
- A UnaryOperator transforms its value into one of the same type.
- For example, incrementing by one is a unary operation. In fact, Unary Operator extends Function.
- A BinaryOperator merges two values into one of the same type. Adding two numbers is a binary operation. Similarly, 
  BinaryOperator extends BiFunction. The interfaces are defined as follows:

```java
import java.util.function.Function;

@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T>{ }
```

```java
import java.util.function.BiFunction;

@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T>{
    // omitted static methods
}
```

- This means that method signature look like this:

```
  T apply(T t);         // UnaryOperator
  T apply(T t1, T t2);  // BinaryOperator
```

- In the Javadoc, you'll notice that these methods are actually inherited from the Function/BiFunction superclass.
- The generic declarations on the subclass are what force the type to be the same. For the unary example, notice how the 
  return type is the same type as the parameter.

```
UnaryOperator<String> u1 = String::toUpperCase();
UnaryOperator<String> u2 = x -> x.toUpperCase();

System.out.println(u1.apply("chirp")); // CHIRP
System.out.println(u2.apply("chirp")); // CHIRP
```

- This prints CHIRP twice. We don't need to specify the return type in the generics because UnaryOperator requires it
  to be the same as the parameter. And now here's the binary example:

```
BinaryOperator<String> b1 = String::concat;
BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);

System.out.println(b1.apply("baby","chick")); // baby chick
System.out.println(b2.apply("baby","chick")); // baby chick
```

- Notice that this does the same thing as the BiFunction example. The code is more succint, which shows the importance 
of using the correct functional interface. It's nice to have one generic type specified instead of three.


## Convenience Methods on Functional Interfaces

- By definition, all functional interface have a single abstract method. This doesn't mean they can have only one method,
  though. Several of the common functional interfaces provide a number or helpful default methods.

- Table 4.2 shows the convenience methods on the built-in functional interfaces that you need to know for the exam.
- Table 4.2 shows only the main interfaces. The BiConsumer, BiFunction, and BiPredicate interfaces have similar methods 
  available.

- Let's start with these two Predicate variables.

```
Predicate<String> egg = s -> s.contains("egg");
Predicate<String> brown = s -> s.contains("brown");
```

- TABLE 4.2 Convenience methods

| Interface instance | Method return type | Method name | Method parameters |
|--------------------|--------------------|-------------|-------------------|
| Consumer           | Consumer           | andThen()   | Consumer          |
| Function           | Function           | andThen()   | Function          |
| Function           | Function           | compose()   | Function          |
| Predicate          | Predicate          | and()       | Preciate          |
| Predicate          | Predicate          | negate()    | -----             |
| Predicate          | Predicate          | or()        | Predicate         |


- Now we want a Predicate for brown eggs and another for all other colors of eggs. We could write this by hand, as shown 
  here:

```
  Predicate<String> brownEggs = 
      s -> s.contains("egg") && s.contains("brown");
  Predicate<String> otherEggs =
      s -> s.contains("egg") && ! s.contains("brown");
```

- This works, but it's not great. It's a bit long to read, and it contains duplication.
- What if we decide the letter e should be capitalized in egg? We'd have to change it in
  three variables: egg, brownEggs, and otherEggs. A better way to deal with this situation is to
  use two of the default methods on Predicate.

```
  Preicate<String> brownEggs = egg.and(brown);
  Predicate<String> otherEggs = egg.and(brown.negate());
```

- Neat! Now we are reusing the logic in the original Predicate variables to build two new ones.
- It's shorter and clearer waht the relationship is between variables. We can change the spelling of egg in one place,
  and the other two objects will have new logic because they reference it.


- Moving to consumer, let's take a look at the andThen() method, which run two functional interfaces in sequence.

```
  Consumer<String> c1 = x -> System.out.println("1: " + x);
  Consumer<String> c2 = x -> System.out.println(",2 " + x);
  
  Consumer<String> combined = c1.andThen(c);
  combined.accept("Annie"); // 1: Annie,2: Annie
```

- This shows that the Consumer instance are run in sequence and are independent of each other.
- By contrast, the compose() method on Function chains functional interfaces. However, it passes along the output of one
  to the input of another.


```
  Function<Integer, Integer> before = x -> x + 1;
  Function<Integer, Integer> after = x -> x * 2;
  
  Function<Integer, Integer> combined = after.compose(before);
  System.out.println(combined.apply(3));  // 8

```

- This time the before runs first, truning the 3 into a 4. Then the after runs, doubling the 4 to 8. All of the methods
  in this section are helpful in simplifying your code as you work with functional interfaces.


## Returning an Optional

- How do we express this "we don't know" or "not applicable" answer in Java? We use the Optional type.
- An Optional is created using a factory.
- You can either request an empty Optional or pass a value for the Optional to wrap.
- Think of an Optional as a box that might have something in it or might instead be empty.

## Creating an Optional


i.e: chapter_4.optional.OptionalExample.java

```
10:  public static Optional<Double> average(int... scores){
11:    if (scores.length == 0) return Optional.empty();
12:    int sum = 0;
13:    for (int score: scores) sum += score;
14:    return Optional.of((double) sum / scores.length)
15:  }
```

- Calling the method show what is in our two boxes.

```
  System.out.println(average(90, 100)); // Optional[95.0]
  System.out.println(average()); // Optional.empty
```

- You can see that one Optional contains a value and the other is empty. Normally, we want to check whether a value is
  there and/or get it out of the box. Here's one way to do that:

```
20: Optional<Double> opt = average(90, 100);
21: if (opt.isPresent())
22:    System.out.println(opt.get()); // 95.0
```

- Line 21 checks whether the Optional actually contains a value. Line 22 prints it out. What if we didn't do the check
  and the Optional was empty?

```
26: Optional<Double> opt = everage();
27: System.out.println(opt.get()); //95.0
```

- We'd get an exception since there is no value inside the Optional.

```
java.util.NoSuchElementException: No value present
```

- When creating an Optional, it is commomn to want to use empty() when the value is null. You can do this with an if 
  statement or ternary operator. We use the ternary operator(? :) to simplify the code.

```
Optional o = (value == null) ? Optional.empty() : Optional.of(value);
```

- If value is null, o is assigned the empty Optional. Otherwise, we wrap the value. Since this is such a common pattern,
  Java provides a factory method to do the same thing.

```
Optional o = Optional.ofNullable(value);
```

Table 4.3 Optional instance methods

| Method                  | When Optional is empty                      | When Optional contains value |
|-------------------------|---------------------------------------------|------------------------------|
| get()                   | Throws an exception                         | Returns value                |
| ifPresent (Consumer c)  | Does nothing                                | Calls Consumer with value    |
| isPresent()             | Returns false                               | Returns true                 |
| orElse(T other)         | Returns other parameter                     | Returns value                |
| orElseGet(Supplier s)   | Returns result of calling Supplier          | Returns value                |
| orElseThrow()           | Throws NoSuchElementException               | Returns value                |
| orElseThrow(Supplier s) | Throws exception create by calling Supplier | Returns value                |


- You've already seen get() and isPresent(). The other methods allow you to write code that uses an Optional in one line
  without having to use the ternary operator. This makes the code easier to read.


## Dealing with an Empty Optional

- The remaining methods allow you to specify what to do if a value isn't present. There are a few choices. The first two
  allow to specify a return value either directly or using a Supplier.


i.e: java.util.Optional.OptionalExample.java
```
30: Optional<Double> opt = average();
31: System.out.println(opt.orElse(Double.NaN));
32: System.out.println(opt.orElseGet(() -> Math.random());
```

- This prints something like the following:

```
NAN
0.49775932295380165
```

- We can have the code throw an exception if the Optional is empty.

```
30: Optional<Double> opt = evarage();
31: System.out.println(opt.orElseThrow()); 
```

- This prints something like the following:

```
Exception in thread "main" java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.orElseThrow(Optional.java:377)
	at chapter_4.optional.OptionalExample.main(OptionalExample.java:41)
```

- Without specifying a Supplier for the exception. Java will throw a NoSuchElementException. This method was added in 
Java 10. Remember that the stack trace looks weird because the lambdas are generated rather than names classes. 
- Alternatively, we can have the code throw a custom exception if the Optional is empty.

```
30: Optional<Double> opt = everage();
31: System.out.println(opt.orElseThrow(
32:      () -> new IllegalStateException())); 
```

- This prints something like the following:

```
Exception in thread "main" java.lang.IllegalStateException    
    at optionals.Methods.lambda$orElse$1(Methods.java:30)    
    at java.base/java.util.Optional.orElseThrow(Optional.java:408)
```

- Line 32 shows using a Supplier to create an exception that should be thrown. Notice that we do not write throw new
  IllegalStateException(). The orElseThrow() method takes care of actually throwing the exception when we run it.

- The two methods that takes a Supplier have different names. Do you see why this code does not compile?

```
  System.out.println(opt.orElseGet( 
      () -> new IllegalStateException())); // DOES NOT COMPILE
```

- The opt variable is an Optional<Double>. This means the Supplier must return a Double. Since this supplier returns an
exception, the type does not match.



## Is Optional the Same as null?

- There were a few shorcommings with this approach. 
- One was that there wasn't a clear way to express that null might be a special value. By contrast, returning an Optional
is a clear statement in the API that there might not be a value in there.
- Another advantage of Optional is that you can use a functional programming style with ifPresent() and the other methods
rather than needing an if statement. Finally, you'll see towards the end of the chapter that you can chain Optional calls.


## Using Streams

- A stream in Java is a sequence of data. A stream pipeline consist of the operations that run on a stream to produce a 
  result.

## Understanding the Pipeline Flow

- Think of a stream pipeline as an assembly line in a factory.
- Another important feature of an assembly line is that each person touches each element to do their operation and then
  that piece of data is gone. It doesn't come back.
- The streams is different than the list and queues.
- With a list, you can access any element at any time.
- With a queue, you are limited in which elements you can access, but all of the element are there.
- With streams, the data isn't generated up front - it is created when needed. This is an example of lazy evaluation, 
  which delays execution until necessary.
- In functional programming, these are called stream operations.
- There are three parts to a stream pipeline, as shown in Figure 4.2.
  - Source: Where the stream comes from
  - Intermediate operations: Transforms the stream into another one. There can be as few or as many intermediate
    operations as you'd like. Since streams use lazy evaluation, the intermediate operations do not run until the 
    terminal operation runs.
  - Terminal operation: Actually produces a result. Since streams can be used only once, the stream is no longer 
    valid after a terminal operation completes.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_4/images/figure_4_2.png?raw=true)


- You will need to know the difference between intermediate and terminal operations well. Make sure you can fill in 
  Table 4.4.

| Scenario                                | Intermediate operation | Terminal operation |
|-----------------------------------------|------------------------|--------------------|
| Required part of a useful pipeline?     | No                     | Yes                |
| Can exist multiple times in a pipeline? | Yes                    | No                 |
| Return type is a stream type            | Yes                    | No                 |
| Executed upon method call?              | No                     | Yes                |
| Stream valid after call?                | Yes                    | No                 |


## Creating Stream Sources

- In Java, the streams we have been talking about are represented by the Stream<T> interface, defined in the 
  java.util.stream package

## Creating Finite Streams

- For simplicity, we'll start with finite streams. There are a few ways to create them.

- i.e: chapter_4.streams.CreateStreamExample.java
```
11: Stream<String> empty = Stream.empty();          // cout = 0
12: Stream<Integer> singleElement = Stream.of(1);   // count = 1
13: Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
```
- Note: Line 13 shows how to create a stream from a varargs. You've undoubtedly noticed that there isn't any array on 
  line 13. The method signature uses varargs, which let you specify an array or individual elements.

- Java also provides a convenient way of converting a Collection to a stream.

```
14: var list = List.of("a", "b", "c");
15: Stream<String> fromList = list.stream();
```

- Line 15 shows that it is a simple method call to create a stream from a list.
- This is helpful since such conversions are common.


## Creating a Parallel Stream

- It is just as easy to create a parallel stream from a list.

```
24: var list = List.of("a", "b", "c");
25: Stream<String> fromListParallel = list.parallelStream();
```

- This is a great feature because you can write code that uses concurrency before learning what a thread is.


## Creating Infinite Streams

- So far, this isn't particularly impressive. We could do all this with lists. We can't create an infinite list, though,
  which makes streams more powerful.

```
17: Stream<Double> randoms = Stream.generate(Math::random);
18: Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
```

- Line 17 generates a stream of random numbers. How many random numbers? However many you need. If you call 
  randoms.forEach(System.out::println), the program will print random numbers until you kill it.
- Line 18 gives you more control. The interate() method takes a seed or starting value as the first parameter. 
  This is the first element that will be part of the stream. The other parameter is a lambda expression that gets
  passed the previous value and generates the next value.

- What if you wanted just odd numbers less than 100?

```
19: Stream<Integer> oddNumberUnder100 = Stream.iterate(
20: 1,              //seed 
21: n -> n < 100,   //Predicate to specify when done
22: n -> n + 2);    // UnaryOperator to get next value
```


## Reviewing Stream Creating Methods

- To review, make sure you know all the methods in Table 4.5. These are the ways of creating a source for streams,
  given a Collection instance coll.


| Method                                         | Finite or infinite? | Notes                                                                                                                                                                    |
|------------------------------------------------|---------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Stream.empty()                                 | Finite              | Creates Stream with zero elements                                                                                                                                        |
| Stream.of(varargs)                             | Finite              | Creates Stream with elements listed                                                                                                                                      |
| coll.stream()                                  | Finite              | Creates Stream from a Collection                                                                                                                                         |
| coll.parallelStream()                          | Finite              | Creates Stream from a Collection where the stream can run in parallel                                                                                                    |
| Stream.generate(supplier                       | Infinite            | Creates Stream by calling the Supplier for each element upon request                                                                                                     |
| Stream.iterate(seed, unaryOperator)            | Infinite            | Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request                                       |
| Stream.iterate(seed, predicate, unaryOperator) | Finite or infinite  | Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request. Stops if the Predicate returns false |



## Using Common Terminal Operations

- You can perform a terminal operation without any intermediate operations but not the other way around.
- This is why we will talk about terminal operations first.
- Reductions are a special type of terminal operation where all of the contents of the stream are combined into a single
  primitive or Object.


- Table 4.6 summarizes this section. Feel free to use it as a guide to remember the most important points as we go 
through each one individually. We explain them from simplest to most complex rather than alphabetically.

| Method                              | What happens for infinite streams | Return value | Reduction |
|-------------------------------------|-----------------------------------|--------------|-----------|
| count()                             | Does not terminate                | long         | Yes       |
| min(); max()                        | Does not terminate                | Optional<T>  | Yes       |
| findAny(); findFirst()              | Terminates                        | Optional<T>  | No        |
| allMatch(); anyMatch(); noneMatch() | Sometimes terminate               | boolean      | No        |
| forEach()                           | Does not terminate                | void         | No        |
| reduce()                            | Does not terminate                | Varies       | Yes       |
| collect()                           | Does not terminate                | Varies       | Yes       |


### count()

- The count() method determines the number of elements in a finite stream.
- For an infinite stream, it never terminates. Why? Count from 1 to infinity and let us know when you are finished.

```
long count()
```

- This example shows calling count() on a finite stream:

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodCount()
```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
System.out.println(s.count()); //3
```

### min() and max()

- The min() and max() methods allow you to pass a custom comparator and find the smallest or largest value in a finite 
  stream according to that sort order.
- Like the count() method, min() and max() hang on an infinite stream because they cannot be sure that a smaller or 
  larger value isn't coming later in the stream.
- Both mehtods are reductions because they return a single value after looking at the entire stream.


- The method signatures are as follows:

```
Optional<T> min(Comparator<? super T> comparator)
Optional<T> max(Comparator<? super T> comparator)
```

- This example finds animal with the fewest letters in its name:


i.e: chapter_4.streams.TerminalOperationsExamples.java#methodMin()
```
Stream<String> s = Stream.of("monkey", "ape", "bonobo");
Optional<String> min = s.min((s1, s2) -> s1.length - s2.length);
min.ifPresent(System.out::println); // ape
```

- As an example of where there isn't a minimum, let's look at an empty stream.

```
Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
System.out.println(minEmpty.isPresent());  //false
```

- Since the stream is empty, the comparator is never called, and no value is present in the Optional.


Note: You can't not have both (min and max), at least not using these methods. Remember, a stream can have only one 
      terminal operation.


### findAny() and findFirst()

- The findAny() and findFirst() methods return an element of the stream unless the stream is empty.
- If the stream is empty, they return an empty Optional.
- This is the first method you've seen that can terminate with an infinite stream.
- These methods are terminal operations but not reductions.
- The reason is that they sometimes return without processing all of the elements. This means that they return a value
  based on the stream but do not reduce the entire stream into one value.


- The method signature are as follows:

```
Optional<T> findAny()
Optional<T> findFirst()
```

- This example finds an animal:

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodFindAny()
```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
Stream<String> infinite = Stream.generate(() -> "chimp");

s.findAny().ifPresent(System.out::println); //monkey (usually)
infinite.findAny().ifPresent(System.out::println); //chimp
```


### allMatch(), anyMatch(), and noneMatch()

- Those three methods search a stream and return information about how the stream pertains to the predicate.
- These may or may not terminate for infinite streams. It depends on the data. Like the find methods, they are not 
  reductions because they do not necessarily look at all of the elements.
  

- The method signatures are as follows:

```
boolean anyMatch(Predicate <? super T> predicate)
boolean allMatch(Prediate <? super T) predicate)
boolean noneMatch(Predicate <? super T> predicate)
```

- This example checks whether animal names begin with letters:

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodMatches()
```
var list = List.of("monkey", "2", "chimp");
Stream<String> infinite = Stream.generate(() -> "chimp");
Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

System.out.println(list.stream().anyMatch(pred));     // true
System.out.println(list.stream().allMatch(pred));     // false
System.out.println(list.stream().noneMatch(pred));    // false
System.out.println(infinite.anyMatch(pred));          // true  
```


- This shows that we can reuse the same predicate, but we need a different stream each time.
- The anyMatch() method returns true because two of the three elements match.
- The allMatch() method returns false because one doesn't match.
- The noneMatch() method also returns false because one matches.
- On the infinite stream, one match is found, so the call terminates.
- If we called allMatch(), it would run until we killed the program.


Note: Remember that allMatch(), anyMatch(), and noneMatch() return a boolean.
      By contrast, the find method return an Optional because they return an element of the stream.


### forEach()

- Like in the Java Collections Framework, it is common to iterate over the elements of a stream. As expected, calling
  forEach() on a infinite does not terminate. Since there is no return value.

- Te method signature is as follow:


```
void forEach(Consumer<? super T> action)
```

- Notice that this is the only terminal operation with a return type of void.

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodForEach()
```
Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
s.forEach(System.out::print); // MonkeyGorillaBonobo
```

Note 1: Remember that you can call forEach() directly on a Collection or on a Stream.
Note 2: Don't get confused on the exam when you see both approaches.


- Notice that you can't use a traditional for loop an a stream.

```
Stream<Integer> s = Stream.of(1);
for (Integer i : s) {} // DOES NOT COMPILE
```

## Reduce

- The reduce() method combines a stream into a single object. It is a reduction, which means it process all elements.

```
T reduce(T identity, BinaryOperator<T> accumulator)

Optional<T> reduce(BinaryOperator<T> accumulator)

<U> U reduce(U identity, BiFunction<U, ? super T,U> accumulator, BinaryOperator<U> combiner)

```

- The most common way of doing a reduction is to start with an initial value and keep merging it with the enxt value.
- Think about how you would concatenate an array of String object into a single String without functional programming.
- It might look something like this:

```
var array = new String[] {"w","o","l","f"}
var result = "";
for (var s: array) result = result + s;
System.out.println(result); // wolf
```

- Notice how we still have the empty String as the identity. We also still concatenate the String object to get the 
  next value. We can even rewrite this with a method reference.

```
Stream<String> stream = Stream.of("w","o","l","f");
String word = stream.reduce("", String::concat);
System.out.println(word); // wolf
```

- Let's try another one. Can you write a reduction to multiply all of the Integer objects in a stream? Try it. Our 
  solution is shown here:

```
Stream<Integer> stream = Stream.of(3, 5, 6);
System.out.println(stream.reduce(1, (a, b) -> a*b));  //90
```


- We set the identity to 1 and the accumulator to multiplication. In many cases, the identity isn't really necessary, so
  Java lets us omit it. When you don't specify an identity, an Optional is returned because there might not be any data.
  There are three choices for what is in the Optional.
  - If the stream is empty, an empty Optional is returned.
  - If the stream has one element, it is returned.
  - If the stream has multiple elements, the accumulator is applied to combine them.
- The following illustrate each of these scenarios:


```
BinaryOperator<Integer> op = (a, b) -> a * b;

Stream<Integer> empty = Stream.empty();
Stream<Integer> oneElement = Stream.of(3);
Stream<Integer> threeElements = Stream.of(3, 5, 6);

empty.reduce(op).ifPresent(System.out::println);           // no output
oneElement.reduce(op).ifPresent(System.out::println);      // 3
threeElements.reduce(op).ifPresent(System.out::println);   // 90
```



- The third method signature is used when we are dealing with different types. It allows Java to create intermediate
  reduction and then combine them at the end. Let's take a look at an example that counts the number of characters in 
  each String.

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodReduce3()

```
 Stream<String> stream = Stream.of("w", "o", "l", "f!");
 int length = stream.reduce(0, (i, s) ->  i+s.length(), (a, b) -> a + b);
 System.out.println(length);
```

- The first parameter (0) is teh value for the initializer.
- If we had an empty stream, this would be the answer.
- The second parameter is the accumulator. Unlike the accumulators you saw previously, this one handles mixed data types.
- In this example, the first argument, i, is an Integer, while the second argument, s, is a String. It adds the length
  of the current String to our running total.
- The third parameter is called the combiner, which combines any intermediate totals. In this case, a and b are both 
  Integer values.

- The three-argument reduce() operation is useful when working with parallel streams because it allows the stream to be
  decomposed and reassembled by separate threads. For example, if we needed to count the length of four 100-character
  strings, the first two values and the last two values could be computed independently. The intermediate result
  (200 + 200) would then be combined into the final value.



### Collect()

- The collect() method is a special type of reduction called a mutable reduction.
- Common mutable objects include StringBuilder and ArrayList.
- This is a really useful method, because it lets us get data out of streams and into another form.

```

<R> R collect(Supplier<R> supplier, 
    BiConsumer<R, ? super T> accumulator,
    BiConsumer<R, R> combiner)

<R,A> R collect(Collector<? super T, A,R> collector)
```

- Let's start with the first signature, which is used when we want to code specifically how collecting should work.
- Our wolf example from reduce can be converted to use collect().

```
Stream<String> stream = Stream.of("w", "o", "l", "f");
  StringBuilder word = stream.collect(StringBuilder::new,
  StringBuilder::append,
  StringBuilder::append);

System.out.println(word); // wolf
```

- The first parameter is the supplier, which creates the object that will store the result as we collect data.
- The second parameter is the accumulator, which is a BiConsumer that takes two parameter and doesn't return anything.
  In this example, it appends the next String to the StringBuilder.
- The final parameter is the combiner, which is another BiConsumer.It is responsible for taking two data collection and 
  merging them. This is useful when we are processing in parallel. Two smaller collections are formed and then merged 
  into one. This would work with StringBuilder only if we didn't care about the order of the letters. In this case, the
  accumulator and combiner have similar logic.

- Now let's look at an example where the logic is different in the accumulator and combiner.


i.e: i.e: chapter_4.streams.TerminalOperationsExamples.java#methodCollect2()
```
Stream<String> stream = Stream.of("w", "o", "l", "f");

TreeSet<String> set = stream.collect(
  TreeSet::new, 
  TreeSet::add, 
  TreeSet::addAll);
  
System.out.println(set); //[f, l, o, w]

```

- The collector has three parts as before.
- The supplier creates an empty TreeSet.
- The accumulator adds a single String from the Stream to the TreeSet.
- The combiner adds all of the elements of one TreeSet to another in case the operations were done in parallel and need 
  to be merged.

- In practice, there are many common collectors that come up over and over. 
- Rather than making developers keep reimplementing the same ones, Java provides a class with common collectors cleverly 
  named Collectors. This approach also makes the code easier to read because it is more expressive.

i.e: i.e: i.e: chapter_4.streams.TerminalOperationsExamples.java#methodCollect3()

```
Stream<String> stream = Stream.of("w", "o", "l", "f");
TreeSet<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
System.out.println(set); // [f, l, o, w]
```

- If we didn't need the set to be sorted, we could make the code even shorter:

```
Stream<String> stream2 = Stream.of("w", "o", "l", "f");
Set<String> set2 = stream2.collect(Collectors.toSet());
System.out.println(set2); // [f, w, l, o]
```

- You might get different output for this last one since toSet() makes no guarantees as to which implementation of Set
  you'll get. It is likely to be a HashSet, but you shouldn't expect or rely on that.


## Using Common Intermediate Operations

- Unlike a terminal operation, an intermediate operation produces a stream as its result. An intermediate operation can 
  also deal with an infinite stream simply by returning another infinite stream. Since elements are produced only as 
  needed, this works fine. The assembly line worker doesn't need to worry about how many more elements are coming
  through and instead can focus on the current element.

### filter()

- The filter() method returns a Stream with elements that match a given expression. 
- Here is the method signature:

```
Stream<T> filter(Predicate<? super T> predicate)
```

- This operation is easy to remember and powerful because we can pass any Predicate to it. For example, this filters
  all elements that begin with the letter m:

i.e: chapter_4.streams.IntermediateOperationsExample.java#methodFilter()
```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
ss.filter(x -> x.startsWith("m"))
    .forEach(System.out::print); // monkey
```


### limit() and skip()

- The limit() and skip() methods can make a Stream smaller, or they could make a finite stream out of an infinite stream.
- The method signatures are shown here:

```
Stream<T> limit(long maxSize)
Stream<T> skip(long n)
```

- The following code creates an infinite stream of number counting from 1.
- The skip() operation returns an infinite stream starting with the numbers counting from 6, since it skips
  the first five elements.
- The limit() call takes the first two of those. 
- Now we have a finite stream with two elements, which we can then print with the forEach() method.

i.e: chapter_4.streams.IntermediateOperationsExample.java#methodLimitAndSkip()
```
Stream<Integer> s = Stream.iterate(1, n -> n + 1);
s.skip(5)
  .limit(2)
  .forEach(System.out::print);  // 67
```

### map()

- The map() method creates a one-to-one mapping from the elements in the stream to the elements of the next step in the
  stream.
- The method signature is as follows:

```
<R> Stream<R> map(Function<? super T, ? extends R> mapper)
```

- It uses the lambda expression to figure out the type passed to that function and the one returned.
- The return type is the stream that gets returned.

Note: The map() method on streams is for transforming data. Don't confuse it with the Map interface, which maps keys to
      values.

- As an example, this code converts a list of String objects to a list of Integer objects representing their lenghts.

i.e: chapter_4.streams.IntermediateOperationsExample.java#methodMap()
```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo);
s.map(String::length)
  .forEach(System.out::print); // 676
```

- Remember that String::length is shorthand for the lambda x -> x.length(), which clearly shows it is a function that
  turns a String into a Integer.


### flatMap()

- The flatMap() method takes each element in the stream and makes any elements it contains top-level elements in a 
  single stream. This is helpful when you want to remove empty elements from a stream or you want to combine a stream of 
  lists.
- You aren't expected to be able to read this:


```
<R> Stream<R> flatMap(
  Function<? super T, ? extends Stream<? extends R>> mapper)
```

- This gibberish basically says that it returns a Stream of the type that the function contains at a lower level.
- Don't worry about the signature. It's a headache.
- What you should understand is the example. This gets all of the animals into the same level along with getting rid of 
  the empty list.

i.e: chapter_4.streams.IntermediateOperationsExample.java#methodFlatMap()
```
List<String> zero = List.of();
var one = List.of("Bonobo");
var two = List.of("Mama Gorilla", "Baby Gorilla");
Stream<List<String>> animals = Stream.of(zero, one, two);

animals.flatMap(m -> m.stream())
    .forEach(System.out::println);
```

- Here's the output:

```
Bonobo
Mama Gorilla
Baby Gorilla
```

- As you can see, it removes the empty list completely and changed all elements of each list to be at the top level of
  the stream.

### sorted()

- The sorted() method returns a stream with the elements sorted. Just like sorting arrays, Java uses natural ordering 
  unless we specify a comparator. The method  signatures are these:

```
Stream<T> sorted()
Stream<T> sorted(Comparator<? super T> comparator)
```

Calling the first signature uses de default sort order.

i.e: chapter_4.streams.IntermediateOperationsExample.java#methodSorted()
```
Stream<String> s = Stream.of("brown-", bear-);
s.sorted()
  .forEach(System.out::print); // bear-brown-
```

We can optionally use a Comparator implementation via a method or a lambda. In this example, we are using a method.


i.e: chapter_4.streams.IntermediateOperationsExample.java#methodSorted()
```
Stream<String> s = Stream.of("brown bear-", "grizzly-");
s.sorted(Comparator.reverseOrder())
  .forEach(System.out::print);  // grizzly-brown bear-
```

- Here we passed a Comparator to specify that we want to sort in the reverse of natural sort order.

### peek()

- The peek() method is our final intermediate operation. It is useful for debugging because it allows us to perform a
  stream operation without actually changing the stream. The method signature is as follows:

```
Stream<T> peek(Consumer<? super T> action)
```

- You might notice the intermediate peek() operation takes the same argument as the terminal forEach() operation.
- Think of peek as an intermediate version of forEach() that returns the original stream back to you.
- The most common use for peek() is to output the contents of the stream as it goes by.
- Suppose that we made a type and counted bears beginning with the letter g instead of b.
- We are puzzled why the count is 1 instead of 2. We can add a peek() method to find out why.


i.e: chapter_4.streams.IntermediateOperationsExample.java#methodPeek()
```
var stream = Stream.of("black bear", "brown bear", "grizzly");
long count = stream.filter(s -> s.startWith("g"))
    .peek(System.out::println).count();           // grizzly
System.out.println(count);
```

### Danger: Changing State with peek()

- Remember that peek() is intended to perform an operation without changing the result.
- You can write bad peek code. Like this:

```
Stream<List<?>> bad = Stream.of(number, letters);
bad.peek(x -> x.remove(0))
  .map(List::size)
  .forEach(System.out::println()); //00
```

- This example is bad because peek() is modifying the data structure that is used in the stream, which causes
  the result of the stream pipeline to be different than if the peek wasn't present.


### Putting Together the Pipeline

- Streams allow you to use chaining and express what you want to accomplish rather than how to do so.
- Let's say that we wanted to get the first two names of our friends alphabetically that are four characters long.
- Without streams we are focus on how to do not what to do.

```
var list = List.of("Toby", "Anna", "Leroy","Alex");
list.stream()
  .filter(n -> n.lenght() == 4)
  .sorted()
  .limit(2)
  .forEach(System.out::println);
```

- The difference is that we express what is going on.
- Once you start using streams in your code, you may find yourself using them in many places.
- Having shorter, briefer, and clearer code is definitely a good thing!


- You can even chain two pipelines together. See if you can identify the two sources and two terminal operations in 
  this code.

```
30: long count = Stream.of("goldfish", "finch")
31:   .filter(s -> s.length() > 5)
32:   .collect(Collectors.toList())
33:   .stream()
34:   .count()
35: System.out.println(count);    // 1   
```

- Lines 30-32 are one pipeline, and lines 33 and 34 are another. For the first pipeline, line 30 is the source, and 
  line 32 is the terminal operation.
- For the second pipeline, line 33 is the source, and line 34 is the terminal operation.
- Now that's a complicated way of outputting the number 1!

- Our prior example can be written as follows:


```
 long count = Stream.of("goldfish", "finch")
   .filter(s -> s.length() > 5)
   .collect(Collectors.toList())
 long count = helper.stream()
    .count();
System.out.println(count);    // 1   
```

- Which style you use is up to you. However, you need to be able to read both styles before you take the exam.


## Working with Primitive Stream

- Up until now, all of the streams we've created used the Stream class with a generic type, like Stream<String>,
  Stream<Integer>, etc. For numeric values, we have been using the wrapper classes you learned about in Chapter 3.
- We did this with the Collections API so it would feel natural.
- Java actually includes other stream classes besides Stream that you can use to work with select primitives: int, 
  double, and long.
- Let's take a look at why this is needed. 
- Suppose that we want to calculate the sum of numbers in a finite stream.

```
Stream<Integer> stream = Stream.of(1, 2, 3);
System.out.println(stream.reduce(0, (s, n) -> s + n));
```

- Not bad, I wasn't hard to write a reduction. We started the accumulator with zero. We then added each number to that 
  running total as it came up in the stream. There is another way of doing that, shown here:


```
Stream<Integer> stream = Stream.of(1, 2, 3);
System.out.println(stream.mapToInt(x -> x).sum());  // 6
```

- This time, we converted our Stream<Integer> to an IntStream and asked the IntStream to calculate the sum to us.
- An IntStream has many of the same intermediate and terminal methods as a Stream but includes specialized methods for 
 working with numeric data.


- Java recognizes that calculating an everage is a common thing to do, and it provides a method to calculate the average 
  on the stream classes for primitives.

```
IntStream intStream = IntStream.of(1, 2, 3);
OptionalDouble avg = intStream.average();
System.out.println(avg.getAsDouble()); // 2.0
```

- Not only is it possible to calculate the average, but it is also easy to do so.
- Clearly primitives streams are important.
- We will look at creating and using such streams, including optionals and functional interfaces.


## Creating Primitive Streams

- Here are three types of primitive streams.
  - IntStream: Used for the primitive types int, short, byte, and char
  - LongStream: Used for the primitive type long
  - DoubleStream: Used for the primitive types double and float

- Table 4.7 shows some of the methods that are unique to primitive streams. Notice that we don't include methods in the 
  table like empty() that you already know from the Stream interface.

TABLE 4.7 Common primitive stream methods


| Method                                      | Primitive stream                    | Description                                                                                  |
|---------------------------------------------|-------------------------------------|----------------------------------------------------------------------------------------------|
| OptionalDouble average()                    | IntStream; LongStream; DoubleStream | The arithmetic mean of the elements                                                          |
| Stream<T> boxed()                           | IntStream; LongStream; DoubleStream | A Stream<T> where T is the wrapper class associated with the primitive value                 |
| OptionalInt max()                           | IntStream                           | The maximum element of the stream                                                            |
| OptionalLong max()                          | LongStream                          | The maximum element of the stream                                                            |
| OptionalDouble max()                        | DoubleStream                        | The maximum element of the stream                                                            |
| OptionalInt min()                           | IntStream                           | The minimum element of the stream                                                            |
| OptionalLong min()                          | LongStream                          | The minimum element of the stream                                                            |
| OptionalDouble min()                        | DoubleStream                        | The minimum element of the stream                                                            |  
| IntStream range(int a, int b)               | IntStream                           | Returns a primitive stream from a (inclusive) to b (exclusive)                               |
| LongStream range(long a, long b)            | LongStream                          | Returns a primitive stream from a (inclusive) to b (exclusive)                               |
| IntStream rangeClosed(int a, int b)         | IntStream                           | Returns a primitive stream from a (inclusive) to b (inclusive)                               |
| LongStream rangeClosed(long a, long b)      | LongStream                          | Returns a primitive stream from a (inclusive) to b (inclusive                                |
| int sum()                                   | IntStream                           | Returns the sum of the elements in the stream                                                |
| long sum()                                  | LongStream                          | Returns the sum of the elements in the stream                                                |
| double sum()                                | DoubleStream                        | Returns the sum of the elements in the stream                                                |
| IntSummaryStatistics summaryStatistics()    | IntStream                           | Returns an object containing numerous stream statistics such as the average, min, max, etc.  |
| LongSummaryStatistics summaryStatistics()   | LongStream                          | Returns an object containing numerous stream statistics such as the average, min, max, etc.  |
| DoubleSummaryStatistics summaryStatistics() | DoubleStream                        | Returns an object containing numerous stream statistics such as the average, min, max, etc.  |


- Some of the methods for creating a primitive streams are equivalent to how we create the source for a regular Stream.
- You can create an empty stream with this:

```
DoubleStream empty = DoubleStream.empty();
```


- Another way is to use the of() factory method from a single value or by using the varargs overload.

```
DoubleStream oneValue = DoubleStream.of(3.14);
oneValue.forEach(System.out::println);

DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
varargs.forEach(System.out::println);
```


- You can also use the two methods for creating infinite streams, just like we did with Stream.

```
var random = DoubleStream.generate(Math::random);
var fractions = DoubleStream.iterate(.5, d-> d / 2);
random.limit(3).forEach(System.out::println);
fractions.limit(3).forEach(System.out::println);
```

- Since the streams are infinite, we added a limit intermediate operation so that the output doesn't print values forever.
- Suppose that we wanted a stream with the number from 1 through 5.
- We could write this using what we've explained so far:

```
IntStream count = IntStream.iterate(1, n -> n + 1).limit(5);
count.forEach(System.out::println);
```

- This code does print out the numbers 1-5, one per line. However, it is a lot of code to do something so simple.
- Java provides a method that can generate a range of numbers.

```
IntStream range = IntStream.range(1, 6);
range.forEach(System.our::println);
```

- This is better. If we wanted number 1-5, why did we pass 1-6? 
- The first parameter to the range() method is inclusive, which means it includes the number.
- The second parameter to the range() method is exclusive, which means it stops right before that number.


- We want the number 1-5 inclusive. Luckily, there's another method, rangeClosed(), which is inclusive on both parameters.

```
IntStream rangeClosed = IntStream.rangeClosed(1, 5);
rangeClosed.forEach(System.out::println);
```

## Mapping Streams

- Another way to create a primitive stream is by mapping from another stream type.

TABLE 4.8 Mapping methods between types of stream

| Source stream class | To create Stream | To create DoubleStream | To create IntStream | To create LongStream |
|---------------------|------------------|------------------------|---------------------|----------------------|
| Stream<T>           | map()            | mapToDouble()          | mapToInt()          | mapToLong()          |
| DoubleStream        | mapToObj()       | map()                  | mapToInt()          | mapToLong()          |
| IntStream           | mapToObj()       | mapToDouble()          | map()               | mapToLong()          |
| LongStream          | mapToObj()       | mapToDouble()          | mapToInt()          | map()                |

- Obviously, they have to be compatible types for this to work. Java requires a mapping function to be provided as a 
  parameter, for example:

```
Stream<String> objStream = Stream.of("penguin", "fish");
IntStream intStream = objStream.mapToInt(s -> s.length());
```

- Table 4.9 shows the mapping between types of streams

| Source stream class | To create Stream  | To create DoubleStream | To create IntStream | To create LongStream |
|---------------------|-------------------|------------------------|---------------------|----------------------|
| Stream<T>           | Function<T,R>     | ToDoubleFunction<T>    | ToIntFunction<T>    | ToLongFunction<T>    |
| DoubleStream        | DoubleFunction<R> | DoubleUnaryOperator    | DoubleToIntFunction | DoubleToLongFunction |
| IntStream           | IntFunction<R>    | IntToDoubleFunction    | IntUnaryOperator    | IntToLongFunction    |
| LongStream          | LongFunction<R>   | LongToDoubleFunction   | LongToIntFunction   | LongUnaryOperator    |


- You do have to memorize Table 4.8 and Table 4.9. It's not as hard as it might seem.

### Using flatMap()

- The flatMap() method exists on primitive stream as well. It works the same way as on a regular Stream except the method
  name is different.

```
var integerList = new ArrayList<Integer>();
IntStream ints = integerList.Stream()
  .flatMapInt(x -> IntStream.of(x));

DoubleStream doubles = integerList.stream()
  .flatMapToDouble(x -> DoubleStream.of(x));

LongStream longs = integerList.stream()
  .flatMapToLong(x -> LongStream.of(x));

```

- Additionally, you can create a Stream from a primitive stream.

```
private static Stream<Integer> mapping(IntStream stream){
  return stream.mapToObj(x -> x);
}

private static Stream<Integer> boxing(IntStream stream){
  return stream.boxed();
}
```

- The first one uses the mapToObj() method we saw earlier.
- The second one is more succinct. It does not require a mapping function because all it does is autobox each primitive
  to the corresponding wrapper object.
- The boxed() method exists on all three types of primitive streams.


## Using Optional with Primitive Streams

- Earlier in the chapter, we wrote a method to calculate the average of an int[].
- Now we can calculate the average in one line.

```
var stream = IntStream.rangeClosed(1,10);
OptionalDouble optional = stream.average();
```

- Why do not use Optional<Double> rather than OptionalDouble?
- The difference is that OptionalDouble is for a primitive and Optional<Double> is for the Double wrapper class.

```
optional.ifPresent(System.out::println);                  // 5.5
System.out.println(optional.getAsDouble());               // 5.5 
System.out.println(optional.orElseGet(() -> Double.NaN);  // 5.5
```

- The only noticeable difference is that we called getAsDouble() rather than get(). 
- This makes it clear that we are working with a primitive.
- Also, orElseGet() takes a DoubleSupplier instead of a Supplier.

- As with the primitive streams, there are three type-specific classes for primitives.

TABLE 4.10 Optional types for primitives


| Description                    | OptionalDouble | OptionalInt | OptionalLong |
|--------------------------------|----------------|-------------|--------------|
| Getting as a primitive         | getAsDouble()  | getAsInt()  | getAsLong()  |
| orElseGet() parameter type     | DoubleSupplier | IntSupplier | LongSupplier |
| Return type of max() and min() | OptionalDouble | OptionalInt | OptionalLong |
| Return type of sum()           | double         | int         | long         |
| Return type of average()       | OptionalDouble | OptionalInt | OptionalLong |


- Let's try an example to make sure that you understand this.

```
5: LongStream longs = LongStream.of(5, 10);
6: long sum = longs.sum();
7: System.out.println(sum);     // 15
8: DoubleStream double = DoubleStream.generate(() -> Math.PI);
9: OptionalDouble min = doubles.min(); // runs infinietly
```

##  Summarizing Statistics

- You've learned enough to be able to get the maximum value from a stream of int primitives. 
- If the stream is empty, we want to throw an exception.

```
private static int max(IntStream ints) {
  OptionalInt optional = ints.max();
  return optional.orElseThrow(RuntimeException::new);
}
```

- If the optional contains a value, we return it. Otherwise, we throw a new RuntimeException.

- Now we want to change the method to take an IntStream and return a range.
- The range is the minimum value subtracted from the maximum value.
- Uh-oh. Both min() and max() are terminal operations, which means that they use up the stream when they are run.
- We can't run two terminal operations against the same stream.
- Luckily, this is a common problem and the primitive streams solve it for us with summary statistics.
- Statistic is just a big word for a number that was calculated from data.

```
private static int range(IntStream ints){
  IntSummaryStatistics stats = ints.summaryStatistics();
  if (stats.getCount() = 0) throw new RuntimeException();
  returns stats.getMax()-stats.getMin();
}
```

- Here we asked Java to perform many calculations about the stream.
- Summary statistics include the following:
  - Smallest number (minimum): getMin()
  - Largest number (maximum): getMax()
  - Average: getAverage()
  - Sum: getSum()
  - Number of values: getCount()
- If the stream were empty, we'd have a count and sum of zero. The other methods would return an empty optional.


## Learning the Functional Interfaces for Primitives

- Most of the functional interfaces are for double, int, and long to match the stream and optionals that we've been 
  using for primitives.
- You have to memorize TABLE 4.1, TABLE 4.11

- TABLE 4.11 Common functional interfaces for primitives

| Functional interfaces                                       | # parameters                                       | Return type       | Single abstract method                  |
|-------------------------------------------------------------|----------------------------------------------------|-------------------|-----------------------------------------|
| DoubleSupplier; IntSupplier; LongSupplier                   | 0                                                  | double; int; long | getAsDouble; getAsInt; getAsLong        |
| DoubleConsumer; IntConsumer; LongConsumer                   | 1 (double); 1 (int); 1 (long)                      | void              | accept                                  |
| DoublePredicate; IntPredicate; LongPredicate                | 1 (double); 1 (int); 1 (long)                      | boolean           | test                                    |
| DoubleFunction<R>; IntFunction<R>; LongFunction<R>          | 1 (double); 1 (int); 1(long)                       | R                 | apply                                   |
| DoubleUnaryOperator; IntUnaryOperator; LongUnaryOperator    | 1 (double); 1 (int); 1 (long)                      | double; int; long | applyAsDouble; applyAsInt; applyAsLong  |
| DoubleBinaryOperator; IntBinaryOperator; LongBinaryOperator | 2 (double, double); 2 (int, int); 2 (long, long)   | double; int; long | applyAsDouble; applyAsInt; applyAsLong  |



- There are a few things to notice that are different between Table 4.1 and Table 4.11.
  - Generic are gone from some of the interfaces, and instead the type name tells us what primitive type is involved.
  - In other cases, such as IntFunction, only the return type generic is needed because we're converting a primitive 
    int into an object.
  - The single abstract method is often renamed when a primitive type is returned.

- In addition to Table 4.1 equivalents, some interfaces are specific to primitives Table 4.12 list these.


- TABLE 4.12 Primitive-specific functional interfaces


| Functional interfaces                                                                                                      | # parameters                                                 | Return type                          | Single abstract method                                                         |
|----------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------|--------------------------------------|--------------------------------------------------------------------------------|
| ToDoubleFunction<T>; ToIntFunction<T>; ToLongFunction<T>                                                                   | 1 (T)                                                        | double; int; long                    | applyAsDouble; applyAsInt; applyAsLong                                         |
| ToDoubleBiFunction<T, U>; ToIntBiFunction<T, U>; ToLongBiFunction<T, U>                                                    | 2 (T, U)                                                     | double; int; long                    | applyAsDouble; applyAsInt; applyAsLong                                         |
| DoubleToIntFunction; DoubleToLongFunction; IntToDoubleFunction; IntToLongFunction; LongToDoubleFunction; LongToIntFunction | 1 (double); 1 (double); 1 (int); 1 (int); 1 (long); 1 (long) | int; long; double; long; double; int | applyAsInt; applyAsLong; applyAsDouble; applyAsLong; applyAsDouble; applyAsInt | 
| ObjDoubleConsumer<T>; ObjIntConsumer<T>; ObjLongConsumer<T>                                                                | 2 (T, double); 2 (T, int); 2 (T, long)                       | void                                 | accept                                                                         |


- When you see something like that. Which functional interface you could use?

```
var d = 1.0;
___________________ f1 = x -> 1;
f1.applyAsInt(d);
```

- When you see a question like this, look for clues. You can see that the functional interface in question takes a 
  a double parameter and returns an int.
- You can also see that it has a single abstract method named applyAsInt.
- The DoubleToIntFunction and ToIntFunction meet all three of those criteria.


## Working with Advanced Stream Pipeline Concepts

- You'll see the relationship between stream and the underlying data, chaining Optional and grouping collectors.


### Linking Stream to the Underlying Data

- What do you think this outputs?

i.e: chapter_4.advanced_stream_pipeline.LinkingStream.java#linkStreamToTheUnderlyingData()
```
25: var cats = new ArrayList<String>();
26: cats.add("Annie");
27: cats.add("Ripley");
28: var stream = cats.stream();
29: cats.add("KC");
30: System.out.println(stream.count());
```

- The correct answer is 3.
- Lines 25-27 create a List with two elements.
- Line 28 requests that a stream be created from that List.
- Remember that streams are ***Lazy evaluated***. This means that the stream isn't actually created one line 28.
- An object is created that knows where to look for the data when it is needed.
- On line 29, the List gets a new element.
- On line 30, the stream pipeline actually runs.
- The stream pipeline runs first, looking at the source and seeing three elements.

### Chaining Optionals

- Suppose that you are given an Optional<Integer> and asked to print the value, but only if it is a three-digit number.
- Without functional programming, you could write the following:



i.e: chapter_4.advanced_stream_pipeline.LinkingStream.java#threeDigitRegularWay()
```
private static void threeDigit(Optional<Integer> optional){
  if (optional.isPresent()) {  // outer if
    var num = optional.get();
    var string = "" + num;
    if (string.length() == 3) // inner if
      System.out.println(string);
  }
}
```


- It works, but it contains nested if statements. That's extra complexity.
- Let's try this again with functional programming.

i.e: chapter_4.advanced_stream_pipeline.LinkingStream.java#threeDigit()
```
private static void threeDigit(Optional<Integer> optional) {
  optional.map(n -> "" + n)           // part 1
    .filter(s -> s.length() == 3)     // part 2  
    .ifPresent(System.out::println);  // part 3
}
```

- This is much shorter and more expressive.
- With lambdas, the exam is fond of carving up a single statement and identifying the pieces with comments.


- Now suppose that we wanted to get an Optional<Integer> representing the length of the String contained in another 
  Optional. Easy enough.

```
Optional<Integer> result = optional.map(String::length);
```

- What if we had a helper method that did the logic of calculating something for us that returns Optional<Integer>? 
  Using map doesn't work.


```
Optional<Integer> result = optional
  .map(ChainingOptionals::calculator);  //DOES NOT COMPILE
```

- The problem is that calculator returns Optional<Integer>.
- The map() method adds another Optional, giving us Optional<Optional<Integer>>.
- Well, that's not good. The solution is to call flatMap() instead.

```
Optional<Integer> result = optional
    .flatMap(ChainingOptionals::calculator);
```

- This one works because ***flatMap removes the unnecessary layer***.
- In other words, it flattens the result.
- Chaining calls to flatMap() is useful when you want to transform one Optional type to another.


## Real World Scenario

### Checked Exception and Functional Interfaces

- You might have notice by now that most functional interfaces do not declare checked exceptions.
- This is normally OK.
- However, it is a problem when working with methods that declare checked exceptions.
- Suppose that we have a class with a method that throws a checked exception.

```java
import java.io.*;
import java.util.*;

public class ExceptionCaseStudy {
    
    private static List<String> create() throws IOException {
        throw new IOException();
    }
}

```

- Now we use it in a stream.

```
public void good() throws IOException {
    ExceptionCaseStudy.create().stream().count();
        }
```

- Nothing new here. The create() method throws a checked exception.
- The calling method handles or declares it.
- Now what about this one?

```
public void bad() throws IOException {
  Supplier<List<String>> s = ExceptionCaseStudy::create; // DOES NOT COMPILE
}
```

- The actual compiler error is as follows:

```
  unhandled exception type IOException
```

- The problem is that the lambda to which this method reference expands does not declare an exception.
- The Supplier interface does not allow checked exceptions.
- There are two approaches to get around this problem.

- First approach is to catch the exception and turn it into an unchecked exception.

```
public void ugly() {
  Supplier<List<String>> s = () -> {
    try{
        return ExceptionCaseStudy.create();
    } catch (IOException e){
      throw new RuntimeException(e);
    }
  }
}
```

- This works. But the code is ugly
- Another alternative is to create a wrapper method with the try/catch.


```
private static List<String> createSafe() {
  try{
    return ExceptionCaseStudy.create();
  } catch (IOException e) {
    throw new RuntimeException(e);
  }
}
```

- Now we can use the safe wrapper in our Supplier without issue.

```
public void wrapper() {
    Supplier<List<String>> s2 = ExceptionCaseStudy::createSafe;
}
```


## Collecting Results

- Early in the chapter, you saw the collect() terminal operation.
- There are many predefined collectors, including those shown in Table 4.13.
- These collectors are available via static methods on the Collectors interface.

TABLE 4.13 Examples of grouping / partitioning collectors

| Collector                                                                                                                                     | Description                                                                                                            | Return value when passed to collect                                  |
|-----------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| averagingDouble(ToDoubleFunction f); averagingInt(ToIntFunction f); averagingLong(ToLongFunction f)                                           | Calculate the average for our three core primitive types                                                               | Double                                                               |
| counting()                                                                                                                                    | Counts the number of elements                                                                                          | Long                                                                 |
| groupingBy(Function f); groupingBy(Function f, Collector dc), groupingBy(Function f, Supplier s, Collector dc)                                | Creates a map grouping by the specified function with the optional map type supplier and optional downstream collector | Long                                                                 |
| joining (CharSequence cs)                                                                                                                     | Creates a single String using cs as a delimiter between elements if one is specified                                   | String                                                               |      
| maxBy(Comparator c); minBy(Comparator c)                                                                                                      | Finds the largest/smallest elements                                                                                    | Optional<T>                                                          |
| mapping(Function f, Collector dc)                                                                                                             | Adds another level of collectors                                                                                       | Collector                                                            |
| partitioningBy(Predicate p); partitiong(Predicate p, Collector dc)                                                                            | Creates a map grouping by the specified predicate with the optional further downstream collector                       | Map<Boolean, List<T>>                                                |
| summarizingDouble(ToDoubleFunction f); summarizingInt(ToIntFunction f); summarizingLong(ToLongFunction f)                                     | Calculates average, min, max, and so on                                                                                | DoubleSummaryStatistics; IntSummaryStatistics; LongSummaryStatistics |
| toList(); toSet()                                                                                                                             | Creates an arbitrary type of list or set                                                                               | List; Set                                                            |
| toCollection(Supplier s)                                                                                                                      | Creates an arbitrary type of list or set                                                                               | Collection                                                           |
| toMap(Function k, Function v); toMap(Function k, Function v, BinaryOperator m); toMap(Function k, Function v, BinaryOperator m, Supplier s)   | Creates a map using functions to map the keys, values, an optional merge function, and an optional map type supplier   | Map                                                                  |  


### Collecting Using Basic Collectors

- Luckily, many of these collectors work in the same way. Let's look at an example.

i.e: chapter_4.collectors.BasicCollectorsExamples.java#joiningCollector()
```
var ohMy = Stream.of("lions", "tigers", "bears");
String result = ohMy.collect(Collectors.joining(", "));
System.out.println(result); // lions, tigers, bears
```

- Notice how the predefined collectors are in the Collectors class rather than the Collector interface.
- This is a common theme, which you saw with Collection versus Collections.
- You'll see this pattern again in Chapter 9, "NIO.2," when working with Paths and Path, and other related types.

- We pass the predefined joining collector to the collect() method. 
- All elements of the stream are then merged into a String with the specified delimiter between each element.
- It is important to pass the Collector to the collect method. It exists to help collect elements.
- A Collector doesn't do anything on its own.



- Let's try another one.
- What is the average length of the three animal names?


i.e: chapter_4.collectors.BasicCollectorsExamples.java#averingIntCollector()
```
var onMy = Stream.of("lions", "tigers", "bears");
Double result = ohMy.collect(Collectors.averagingInt(String::length));
System.out.println(result); // 5.333333333333333
```

- With primitive streams, the result of an average was always a double, regardless of what type is being average.
- For collectors, it is a Double since those need an Object.

- Often, you'll find yourself interacting with code that was written without streams. 
- This means that it will expect a Collection type rather than a Stream type.
- You can still express yourself using a Stream and then convert to a Collection at the end, for example:


i.e: chapter_4.collectors.BasicCollectorsExamples.java#toCollectionCollector()
```
var ohMy = Stream.of("lions", "tigers", "bears");
TreeSet<String> result = ohMy
  .filter(s -> s.startsWith("t"))
  .collect(Collectors.toCollection(TreeSet::new));

System.out.println(result); // [tigers]
```

- If we didn't care which implementation of Set we got, we could have written Collectors.toSet() instead.


NOTE: At this point, you should be able to use all of the Collectors in TABLE 4.13 except groupingBy(), mapping(),
partitioningBy(), and toMap().


### Collecting into Maps

- Code using Collectors involving maps can get quite long. We will build it up slowly. Make sure that you understand 
  each example before going on to the next one. Let's start with a straightforward example to create a map from a stream.


i.e: chapter_4.collectors.BasicCollectorsExamples.java#mapsCollector()

```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<String, Integer> map = ohMy.collect(
  Collectors.toMap(s -> s, String::length));
System.out.println(map); // {lions=5, bears=5, tigers=6}

```

- When creating a map, you need to specify two functions. The first function tells the collector how to create the key.

Note: Returning the same value into a lambda is a common operation, so Java provides a method for it. You can rewrite
      s -> s as Function.identity(). 

- Let's suppose that our requirement is to create a comma-separated String with the animals names. We could write this:


i.e: chapter_4.collectors.BasicCollectorsExamples.java#maps2Collector()

```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, String> map = ohMy.collect(Collectors.toMap(
    String::length,
    k -> k,
    (s1, s2) -> s1 + "," + s2));
    
System.out.println(map);              // {5=lions,bears, 6=tigers}
System.out.println(map.getClass());   // class java.util.HashMap
```

- It so happens that the Map returned is a HashMap. This behavior is not guaranteed.
- Suppose that we want to mandate that the code return a TreeMap instead. No problem.
- We would just add a constructor reference as a parameter.


i.e: chapter_4.collectors.BasicCollectorsExamples.java#maps3Collector()
```
 var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map = ohMy.collect(
                Collectors.toMap(String::length, s -> s,
                        (s1,s2) -> s1 + "," + s2,
                        TreeMap::new)
        );

System.out.println(map); // {5=lions,bears, 6=tigers}
System.out.println(map.getClass());  //class java.util.TreeMap
```

- This time we got the type that we specified


### Collecting Using Grouping, Partitioning, and Mapping

- Now suppose that we want to get groups of names by their length.
- We can do that by saying that we want to group by length.


i.e: chapter_4.collectors.GroupingByExamples.java#groupingByExample()

```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, List<String>> map = ohMy.collect(
        Collectors.groupingBy(String::length)
);
System.out.println(map);              // {5=[lions, bears], 6=[tigers]}
System.out.println(map.getClass());  //class java.util.HashMap

//Note: groupingBy cannot return null. It doesn't allow null keys.
```

- The groupingBy() collector tells collect() that it should group all of the elements of the stream into a Map. 
- The function determines the keys in the Map.
- Each value in the Map is a List of all entries that match that keys.


- Suppose that we don't want a List as the value in the map and prefer a Set instead.
- No problem. There's another method signature that let us pass a downstream collector.

i.e: chapter_4.collectors.GroupingByExamples.java#groupingBySetExample()
```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Set<String>> map = ohMy.collect(
        Collectors.groupingBy(
                String::length,
                Collectors.toSet()));
                
System.out.println(map); // {5=[lions, bears], 6=[tigers]}

//Note: groupingBy cannot return null. It doesn't allow null keys.
```


- We can even change the type of Map returned through yet another parameter.

i.e: chapter_4.collectors.GroupingByExamples.java#groupingByTreeMapExample()
```
var ohMy = Stream.of("lions", "tigers", "bears");
TreeMap<Integer, Set<String>> map = ohMy.collect(
        Collectors.groupingBy(
                String::length,
                TreeMap::new,
                Collectors.toSet()));
                
System.out.println(map);            // {5=[lions, bears], 6=[tigers]}
System.out.println(map.getClass()); //class java.util.TreeMap
```

- This is very flexible.
- What if we want to change the type of Map returned but leave the type of values alone as a List?

i.e: chapter_4.collectors.GroupingByExamples.java#groupingByTreeMapListExample()
```
var ohMy = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer, List<String>> map = ohMy.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toList()));
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}
        System.out.println(map.getClass()); //class java.util.TreeMap

```



### Partitioning

- Partitioning is a special case of grouping.
- With partitioning, there are only two possible groups-true and false.
- Partitioning is like splitting a list into two parts.


- Suppose that we are making a sign to put outside each animal's exhibit. 
- We have two sizes of signs.
- One can accommodate names with five or fewer characters.
- The other is needed for longer names.
- We can partition the list according to which sign we need.



i.e: chapter_4.collectors.PartitioningByExamples.java#partitioningByExample()
```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, List<String>> map = ohMy.collect(
        Collectors.partitioningBy(s -> s.length() <= 5)
);
System.out.println(map); //{false=[tigers], true=[lions, bears]}
```

- Here we passed a Predicate with the logic for which group each animal name belongs in.


- Now suppose that we've figured out how to use a different font, and seven characteres can now fit on the smaller sign.
- No worries. We just change the Predicate.


```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, List<String>> map = ohMy.collect(
        Collectors.partitioningBy(s -> s.length() <= 7)
);
System.out.println(map); {false=[], true=[lions, tigers, bears]}
```


- Notice that there are still two keys in the map-one for each boolean value.
- As with groupingBy(), we can change the type of List to something else.

```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, Set<String>> map = ohMy.collect(
    Collectors.partitioningBy(s -> s.length() <= 7,
            Collectors.toSet())
);
System.out.println(map); //{false=[tigers], true=[lions, bears]}
```


- Unlike groupingBy(), we cannot change the type of Map that gets returned. However, there are only two keys in the map,
  so does it really matter which Map type we use?

- Instead of using the downstream collector to specify the type, we can use only of the collectors that we've already
  shown. For example, we can group by the length of the animal name to see how many of each length we have.


i.e: chapter_4.collectors.GroupingByExamples.java#groupingByLength()
```
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Long> map = ohMy.collect(
        Collectors.groupingBy(
        String::length,
        Collectors.counting()));

System.out.println(map);  // {5=2, 6=1}
```


### mapping()

- Finally, there is a mapping() collector that lets us go down a level and another collector.
- Suppose that we wanted to get the first letter of the first animal alphabetically of each length.
- Why? Perhaps for random sampling.
- The examples on this part of the exam are fairly contrived as well.
- We'd write the following:


i.e: chapter_4.collectors.GroupingByExamples.java#groupingByMappingExample()
````
var ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Optional<Character>> map = ohMy.collect(
        Collectors.groupingBy(String::length,
                Collectors.mapping(
                        s -> s.charAt(0),
                        Collectors.minBy((a,b) -> a - b)))
);
System.out.println(map); //{5=Optional[b], 6=Optional[t]}
````

- We aren't going to tell you that this code is easy to read.
- We will tell you that it is the most complicated thing you need to understand for the exam.
- Comparing it to the previous example, you can see that we replaced counting() with mapping().
- It so happens that mapping() takes two parameters: the function for the value and how to group it further.


Note: There is one more collector called reducing(). You don't need to know it for the exam.
      It is a general reduction in case all of the previous collectors don't meet your needs.


