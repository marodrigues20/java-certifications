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


