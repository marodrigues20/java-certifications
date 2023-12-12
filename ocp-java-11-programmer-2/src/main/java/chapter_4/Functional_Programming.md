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










