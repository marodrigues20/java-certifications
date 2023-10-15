# Java Fundamentals

## Java Modifier

- final modifier can be applied to:
  - variables (It means the value cannot be changed after it is assigned)
  - methods (It cannot be overriden)
  - classes (It cannot be extended)


## Final Variables Snippet code

- FinalVariableTest.printZooInfo
- FinalVariableTest.printZooInfo_2
- FinalVariableTest.printZooInfo_3


## Final Instance and static Variables

- Instance and static class variables can also be marked final. If an instance is marked final, then it must be assigned
a value when it is declared or when the object is instantiated. Like a local final variable, it cannot be assigned a
a value more than once.

- PolarBear.java

## Static final Variables

The rules for static variables are similar to instance final variables, except they do not use static constructors
(there is no such thing!) and use static initializers instead of instance initializers.

- Panda.java


## Final Methods

- Methods marked final cannot be overridden by a subclass. This essentially prevents any polymorphic behavior on the 
method call and ensure that a specific version of the method is always called.

- Remember that methods can be assigned an abstract or final modifier. 
  1. An abstract method cannot have body 
  2. Can appear only inside an abstract class or interface.
  3. A final method cannot be overridden by a subclass.
  4. A method CANNOT be marked both abstract and final.


### Explanation about 3 bellow classes.

- The chew() method is declared abstract in the Animal class. It is then implemented in the concrete Hippo class, where 
it is marked final, thereby preventing a subclass of Hippo from overriding it. For this reason, the chew() method in 
PygmyHippo does not compile since it is inherited as final.

- Animal.java
- Hippo.java
- PygmyHippo.java

### Example method marked as abstract and final.

- ZooKeeper.java 


## Classes final

- A final classes cannot be extended

### Examples:

- Reptile.java
- Snake.java

- Classes cannot be marked both abstract and final. It is not possible to write a class that provides a concrete 
implementation of the abstract Eagle class, as it is marked final and cannot be extended. The Hawk interface also does
not compile, although the reason is subtler.

  1. The compiler automatically applies the implicit abstract modifier to each interface declaration. 
  2. Just like abstract classes, interface cannot be marked final.
  3. Implicit modifiers are common in interface members, as we will see later.
  

### Examples:

- Eagle.java
- Hawk.java


## Working with Enums

- In programming, it is common to have a type that can only have a finite set of values, such as days of the week, seasons
of the year, primary colors, etc. An enumeration is like a fixed set of constants. In Java, an enum, short for 
"enumerated type" can be a top-level type like a class or interface, as well as a nested type like an inner class.

- Behind the scene enum is a type of class that mainly contains static members. 

### Example:

- Season.java
- EnumTest.java

### Note:

- Enum values are considered constants and are written using snake case e.i: ROCKY_ROAD, MINT_CHOCOLATE_CHIPE and so on.

### YOU CAN'T EXTEND ONE ENUM

- ExtendedSeason.java

## Using Enums in Switch Statement

- SwitchEnum.java

## Adding Constructor, Fields, and Methods

- SeasonV2.java
- SeasonV2Test.java
- OnlyOne.java
- PrintTheOne.java
- SeasonV3.java
- SeasonV4.java

## Created Nested Classes

- A nested class is a class that is defined within another class. A nested class can come in one of 4 (four) flavors.
  1. Inner class: A non-static type defined at the member level of a class.
  2. Static nested class: A static type defined at the member level of a class
  3. Local class: A class defined within a method body.
  4. Anonymous class: A special case of a local class that does not have a name

- There are many benefits of using nested classes. They can encapsulate helper classes by restricting them to the containing
class.

## Declaring an Inner Class

- An inner class, also called a member inner class, is a non-static type defined at the member level of a class. Inner
classes have the following properties:

  1. Can be declared public, protected, package-private (default), or private
  2. Can extend any class and implement interfaces
  3. Can be marked abstract or final
  4. Cannot declare static fields or methods, except for static final fields
  5. Can access members of the outer class including private members

### Example:
  - Outer.java

### .class Files for Inner Classes

- Compiling the Outer.java class with which we have been working creates two class files. Outer.class you should be
expecting. For the inner class, the compiler creates Outer$Inner.class.

- Inner classes can have the same variable names as outer classes, making scope a little tricky.

#### Examples:

- A.java

## Inner Classes Require an Instance

- Fox.java
- Squirrel.java


## Creating a static Nested Class

- A static nested class is a static type defined at the member level.
- Unlike an inner class, a static nested class can be instantiated without an instance of the enclosing class.
- The trade-off, though, is it can't access instance variables or methods in the outer class directly. It can be done
  but requires an explicit reference to an outer class.

- Enclosing.java
- Toucan.java
- BirdWatcher.java

## Writing a local class

- A local class is a nested class defined within a method. Like local variables, a local class declaration does not exist
until the method is invoked, and it goes out of scope when the method returns. This means you can create instances only
from within the method. Those instances can still be returned from the method. This is just how local variables work.

Note: Local classes are not limited to being declared only inside methods. They can be declared inside constructors and
initializers too. This chapter just treat Local classes inside methods.

- Local classes Have the following properties:

1. They do not have an access modifier
2. They cannot be declared static and cannot declare static fields or methods, except for static final fields.
3. They have access to all fields and methods of the enclosing class (when defined in an instance method)
4. They can access local variables if the variable are final or effectively final.

Note: Remember that effectively final refers to a local variable whose value does not change after it is set.


- PrintNumbers.java
- PrintNumbersNotCompile.java

## Defining an Anonymous Class

- An anonymous class is a specialized form of a local class that does not have a name.
- Anonymous classes are required to extend an existing class or implement an existing interface.

### Examples

1. ZooGiftShop.java
2. ZooGiftShopV2.java
3. ZooGiftShopV3.java
4. Gorilla.java


## Reviewing Nested Classes

For the exam, make sure you know the information

### Table 1.1 Modifiers in nested classes

| Permitted Modifiers    | Inner Class  | static nested class  | Local class   | Anonymous class  |
|------------------------|--------------|----------------------|---------------|------------------|
| Access modifiers       | All          | All                  | None          | None             |
| abstract               | Yes          | Yes                  | Yes           | No               |
| Final                  | Yes          | Yes                  | Yes           | No               |


### Table 1.2 Members in nested classes

| Permitted Members    | Inner class    | static nested class | Local class   | Anonymous class |
|----------------------|----------------|---------------------|---------------| --------------- |
| Instance methods     | Yes            | Yes                 | Yes           |       Yes       |
 | Instance variables   | Yes            | Yes                 | Yes           |       Yes       |
 | static methods       | No             | Yes                 | No            |       No        |
 | static variables     | Yes (if final) | Yes                 | Yes (if final)|   Yes (if final)|


### Table 1.3 Nested class access rules

| Objective                                                          | Inner class | static nested class | Local class | Anonymous class                            |
|--------------------------------------------------------------------| ----------- | ------------------- | ------------|--------------------------------------------|
| Can extend any class or implement any number of interfaces         | Yes | Yes | Yes | No-must have exactly one superclass or one interface                       |
| Can access instance members of enclosing class without a reference | Yes | No  | Yes (if declared in an instance method | Yes (if declared in an instance method) |
 | Can access local variables of enclosing method                     | N/A | N/A | Yes (if final or effectively final | Yes (if final or effectively final          |


## Understanding Interface Members

When Java was first released, there were only two types of members an interface declaration could include: abstract methods and constants (static final) variables.
Since Java 8 and 9 were released, four new method types have been added that we will cover in this section.

| Description           | Since Java version | Membership Type | Required modifiers | Implicit modifiers  | Has value or body? |
|-----------------------|--------------------|-----------------|--------------------|---------------------|--------------------|  
| Constant variable     | 1.0                | Class           | --                 | public static final | Yes                |
| Abstract method       | 1.0                | Instance        | --                 | public abstract     | No                 |
| Default method        | 8                  | Instance        | default            | public              | Yes                | 
| Static method         | 8                  | Class           | static             | public              | Yes                |
| Private method        | 9                  | Instance        | private            | --                  | Yes                |
| Private static method | 9                  |  Class          | private static     | --                  | Yes                |


## Relying on a default Interface Method

A default method is a method defined in an interface with the default keyword and includes a method body. <br>
Contrast default methods with abstract methods in an interface, which do not define a method body.

A default method may be overridden by a class implementation the interface. The name default comes from the concept that
it is viewed as an abstract interface method with a default implementation. The class has the option of overriding the 
default method, but if it does not, then the default implementation will be used.

- IsWarmBlooded.java
- Carnivore.java


Note: The default interface method modifier is not the same as the default label used in switch statements. Likewise,
although package-private access is commonly referred to as default access, that feature is implemented by omitting an
access modifier. We agree Java has overused the word default over the years.

## Default Interface Method Definition Rules

1. A default method may be declared only within an interface
2. A default method must be marked with the default keyword and include a method body.
3. A default method is assumed to be public.
4. A default method cannot be marked abstract, final, or static.
5. A default method may be overridden by a class that implements the interface.
6. If a class inherits two or more default methods with the same method signature, then the class must override the method.

## Inheriting Duplicate default Methods

Allowing default methods in interfaces, coupled with the fact that a class may implement multiple interfaces, Java has essentially
opened the door to multiple inheritance problems.

- Walk.java
- Run.java
- Cat.java

If a class implements two interfaces that have default methods with the same method signature, the compiler will report an error.
Note: In this section, all of our conflicting methods had identical declarations. These rules also apply to methods with the same 
signature but different return types or declared exceptions.


## Using static Interface Methods

- Java now supports static interface methods. These methods are defined explicitly with the static keyword and for the
most part behave just like static methods defined in classes

### Static Interface Method Definition Rules

1. A static method must be marked with the static keyword and include a method body.
2. A static method without an access modifiers is assumed to be public.
3. A static method cannot be marked abstract or final.
4. A static method is not inherited and cannot be accessed in a class implementing the interface without a reference to 
the interface name.

These rules should follow from what you know so far of classes, interfaces, and static methods.

- Hop.java
- Bunny.java

## Introducing private Interface Methods

- New to Java 9, interface may now include private interface methods.
- Private methods can be used to reduce code duplication.

- Schedule.java

### Private Interface Method Definition Rules

1. A private interface method must be marked with the private modifier and include a method body.
2. A private interface method may be called only by default and private (non-static) methods within the interface 
definition.

Note: Private Interfaces methods behave a lot like instance methods within a class. Like private methods in a class,
they cannot be declared abstract since they are not inherited.


## Introducing private static Interface Methods

- Swim.java

### Private Static Interface Method Definition Rules

1. A private static method must be marked with the private and static modifiers and include a method body.
2. A private static interface method may be called only by other methods within the interface definition.

Both private and private static methods can be called from default and private methods. This is equivalent to how
an instance method is able to call both static and instance methods. On the other hand, a private method cannot be 
called from a private static method. This would be like trying to access an instance method from a static method 
in a class.

Note: Encapsulation and security work best when the outside caller knows as little as possible about the internal
implementation of a class or an interface. Using private interface methods doesn't just provide a way to reduce code 
duplication, but also a way to hide some of the underlying implementation details from users of the interface.


## Reviewing Interface Members - Table 1.5

| Description           | Accessible from default and private methods within the interface definition | Accessible from static methods within the interface definition? | Accessible from instance methods implementing or extending the interface? | Accessible outside the interface without an instance of interface? |
|-----------------------|-----------------------------------------------------------------------------|-----------------------------------------------------------------|---------------------------------------------------------------------------|--------------------------------------------------------------------|
| Constant variable     | Yes                                                                         | Yes                                                             | Yes                                                                       | Yes                                                                |
| abstract method       | Yes                                                                         | No                                                              | Yes                                                                       | No                                                                 |
| default method        | Yes                                                                         | No                                                              | Yes                                                                       | No                                                                 |
| private method        | Yes                                                                         | No                                                              | No                                                                        | No                                                                 |
| static method         | Yes                                                                         | Yes                                                             | Yes                                                                       | Yes                                                                |
| private static method | Yes                                                                         | Yes                                                             | No                                                                        | No                                                                 |



## Abstract Classes vs. Interfaces

- A key distinction, though, is that interfaces do not implement constructors and are not part of the class hierarchy.
While a class can implement multiple interfaces, it can only directly extend a single class.


## Introducing Functional Programming

Functional interfaces are used as the basis for lambda expressions in functional programming. A functional interfaces is <br>
that contains a single abstract method. <br>
SAM = Single abstract method (SAM) rule.

A lambda expression is a block of code that gets passed around, sort of like an anonymous class that defines one method.

## Defining a Functional Interfaces

Let's take a look at an example of a functional interface and a class that implements it:

- Sprint.java
- Tiger.java

Consider the following for interfaces. Given our previous Sprint functional interfaces, which of the following are
functional interfaces?

- Dash.java (Functional interface)
- Skip.java (Not a valid functional interface)
- Sleep.java (Not a valid functional interface)
- Climb.java (Functional interface)

All four of these are valid interfaces, but not all of them are functional interfaces.

# Declaring a Functional Interfaces with Object Methods

All classes inherit certain methods from Object. For the exam, you should be familiar with the following Object method declaration

- String toString()
- boolean equals(Object)
- int hashCode()

If a functional interface includes an abstract methods with the same signature as a public method found in Object,
then those methods do not count towards the single abstract method test. The motivation behind this rule is that 
any class that implements the interface will inherit from Object, as all classes do, and therefore always implement
these methods.

Note: Since Java assumes all classes from Object, you also cannot declare an interface method that is incompatible 
with Object. For example, declaring an abstract  method "int toString()" in an interface would not compile since 
Object's version of the method returns a String.



# Overriding toString(), equals(Object), and hashCode()

- toString(): The toString() method is called when you try to print an object or concatenate the object with a String.
- equals(Object): The equals(Object) method is used to compare objects, with the default implementation just using the
  == operator. You should override the equals(Object) method anytime you want to conveniently compare elements for 
  equality, specially if this requires checking numerous fields.
- hashCode(): Any time you override equals(Object), you must override hashCode() to be consistent. This means that for 
  any two objects, if a.equals(b) is true, then a.hashCode()==b.hashCode() must also be true.

All of these methods provide a default implementation in Object, but if you want to make intelligent use out of them,
then you should override them.


## Implementing Functional Interfaces with Lambdas

In addition to functional interfaces you write yourself, Java provides a number of predefined ones. You'll learn about 
many of these in Chapter 4, "Functional Programming". For now, let's work with the Predicate interface. Excluding any 
static or default methods defined in the interface, we have the following:

```
  public interface Predicate<T> {
    boolean test(T t);
  }
```

- Any functional interface can be implemented as a lambda expression
- Even older Java interfaces that pass the single abstract method test are functional interfaces, which can be 
  implemented with lambda expressions.


- Animal.java
- TraditionalSearch.java

Note: Lambda expressions rely on the notion of deferred execution. Deferred execution means that code is specified now
but runs later. In this case, later is when the print() method calls it. Even though the execution is deferred, the 
compiler will still validate that the code syntax is correct.


## Writing Lambda Expressions


- The left side of the lambda expression lists the variables. It must be compatible with the type and number of input
  parameter of the functinal interface's single abstract method.
- The right side of the lambda expression represents the body of the expression. It must be compatible with the return
  type of the functional interface's abstract method.

- TraditionalSearch.java

```
 a -> a.canHop()
 | |     |--> body
 | |--------> Arrow
 |----------> Parameter name
```

The code above represents the short form of this functional interface and has three parts:
  1. A single parameter specified with the name 'a'.
  2. The arrow operator to separate the parameter and body.
  3. A body that calls a single method and returns the result of that method.


### Verbose Version

1. A single parameter specified with the name 'a' and stating the type is Animal
2. The arrow operator to separate the parameter and body
3. A body that has one or more lines of code, including a semicolon and a return statement.

```
(Animal a) -> { return a.canHop(); }
        |  |           |--- Body
        |  |---> Arrow
        |-----> Parameter name

```

- The parentheses can be omitted only if there is a single parameter and its type is not explicitly.
- What is different here is that the rules change when you omit the braces. Java doesn't require you to type 'return'
  or use a semicolon when no braces are used.


Note: As a fun fact, 's -> {}' is a valid lambda. If the return type of the functional interface method is void, then
you don't need the semicolon or return statement.

### Valid lambda expressions

```
- () -> new Duck()
- d -> {return d.quack(); }
- (Duck d) -> d.quack()
- (Animal a, Duck d) -> d.quack()
```

### Invalid Syntax

Let's assume we needed a lambda that returns a boolean value.

```
3: a, b -> a.startsWith("test")         // DOES NOT COMPILE - REQUIRE PARENTHESES AROUND EACH PARAMETER LIST
4: Duck d -> d.canQuack();              // DOES NOT COMPILE - REQUIRE PARENTHESES AROUND EACH PARAMETER LIST   
5: a -> { a.startsWith("test"); }       // DOES NOT COMPILE - MISSING RETURN KEYWORD
6: a -> { return a.startsWith("test") } // DOES NOT COMPILE - MISSING THE SEMICOLON INSIDE OF THE BRACES
7: (Swan s, t) -> s.compareTo(t) != 0   // DOES NOT COMPILE - MISSING THE PARAMETER TYPE FOR t. If the paremter type         
                                                              is specified for one of the parameters, then it must be 
                                                              specified for all of them.
```


## Working with Lambda Variable

Variables can appear in three places with respect to lambdas:
1. The parameter list
2. Local variables declared inside the lambda body
3. Variables referenced from the lambda body.


### Parameter List

While you can use 'var' inside a lambda parameter list, there is a rule you need to be aware of. If 'var' is used for
one of the types in the parameter list, then it must be used for all parameters in the list. Given this rule, which 
of the following lambda expressions do no compile if they were assinged to a variable?

```
3: (var num) -> 1                         // COMPILE
4: var w -> 99                            // DOES NOT COMPILE BECAUSE PARENTHESES
5: (var a, var b) -> "Hello"              // COMPILE BECAUSE ALL OF THE PAREMETERS IN THE LIST USE 'var'
6: (var a, Integer b) -> true             // DOES NOT COMPILE BECAUSE THE PARAMETERS TYPES INCLUDE A MIX OF var and type names
7: (String x, var y, Integer z) -> true   // DOES NOT COMPILE BECAUSE THE PARAMETERS TYPES INCLUDE A MIX OF var and type names
8: (var b, var k, var m) -> 3.14159       // COMPILE BECAUSE ALL OF THE PAREMETERS IN THE LIST USE 'var'
9: (var x, y) -> "goodbye"                // DOES NOT COMPILE BECAUSE THE PARAMETER TYPE IS MISSING FOR THE SECOND PARAMETER
```

### Local Variables inside the Lambda Body

- While it is most common for a lambda body to be a single expression, it is legal to define a block. That block can 
have anything that is valid in a normal Java block, including local variables declaration.

- The following code does just that. It creates a local variable named 'c' that is scoped to the lambda block.

```
(a, b) -> { int c = 0; return 5; }
```

Note: When writing your own code, a lambda block with a local variable is a good hint that you should extract that code
into a method.

- Now let's try another one. Do you see what's wrong here?

```
(a, b) -> { int a = 0; return 5; }  // DOES NOT COMPILE
```

- We tried to declare 'a', which is not allowed. Java doesn't let you create a local variable with the same name as one
already declared in that scope.

```
11: public void variables (int a) {
12:  int b = 1;
13:  Predicate<Integer> p1 = a -> {  // Variable 'a' already been used in line 11
14:    int b = 0;                    // Code tries to redeclare local variable in line 12.
15:    int c = 0;
16:    return b == c; 
17:  }                              // The variable p1 is missing a semicolon at the end.
18: }
```

### Variable Referenced from the Lambda Body

- Lambda bodies are allowed to use static variables, instance variables, and local variables if they are final or
  effectively final. Behind the scenes, anonymous classes are used for lambda expression. Let's take a look at an 
  example.

- Crow.java
- CrowV2.java
