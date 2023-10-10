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

| Permitted Modifiers | Inner Class | static nested class | Local class | Anonymous class |
| Access modifiers    |   All       |         All         |     None    |     None        |
| abstract            |   Yes       |         Yes         |     Yes     |     No          |
| Final               |   Yes       |         Yes         |     Yes     |     No          |









