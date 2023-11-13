# Chapter 3 - Generics and Collections

- Generics and Collections
  - Use wrapper classes, autoboxing and autounboxing
  - Create and use generic classes, methods with diamond notation and wildcard
  - Describe the Collections Framework and use key collection interface
  - Use Comparator and Comparable interfaces
  - Create and use convenience methods for collections
- Java Stream API
  - Use lambda expression and method references

We will review all the functional interfaces in Chapter 4, "Functional Programming" but since some will be used in this
chapter, we will provide Table 3.1 as a handy reference. The letters (R,T, and U) are generics that you can pass any
type to when using these functional interfaces.

Table 3.1 Functional interfaces used in this chapter

| Functional Interfaces | Return type | Method name | # parameters |
|-----------------------|-------------|-------------|--------------|
| Supplier<T>           | T           | get()       | 0            |
| Consumer<T>           | void        | accept(T)   | 1(T)         |
| BiConsumer<T, U>      | void        | accept(T,U) | 2(T,U)       |
| Predicate <T>         | boolean     | test(T)     | 1(T)         |
| BiPredicate<T, U>     | boolean     | test(T, U)  | 2(T,U)       |
| Function<T, R>        | R           | apply(T)    | 1(T)         |
| BiFunctinal<T, U, R>  | R           | apply(T, U) | 2(T,U)       |
| UnaryOperator<T>      | T           | apply(T)    | 1(T)         |


For this chapter, you can just use these functional interfaces as is. In the next chapter, though, we'll be presenting
and testing your knowledge of them.

## Using Method References

