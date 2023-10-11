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










