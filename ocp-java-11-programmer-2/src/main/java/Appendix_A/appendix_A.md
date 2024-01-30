# Appendix A - The Upgrade Exam

- Local Variable Type Inference
  - Use local variable type inference
  - Create and use lambda expression with local variable type inferred parameters
- Understanding Modules
  - Describe the Modular JDK
  - Declare modules and enable access between modules
  - Describe how a modular project is compiled and run


- Resources section of the online test bank in our 1ZO-815 GitHub repo linked to from 
  https://github.com/boyarsky/sybex-1Z0-816-chapter-6


## Working with Local Variable Type Inference

- Starting in Java 10, you have the option of using the keyword var instead of the type for local variables under 
  certain conditions. To use this feature , you just type var instead  of the primitive or reference type within a code 
  block.

```java
package Appendix_A.type_reference;

public class VarKeyword {
    
    //var tricky = "Hello!";  // DOES NOT COMPILE
    
    public void whatTypeAmI(){
        var name = "Hello";
        var size = 7;
    }
    
}
```

- The formal name for var keyword is local variable type inference.
- You can use this feature only for local variables.
- Local variable type inference works with local variables and not instance variables.


## Type Inference of var

- When you type var, you are instructing the compiler to determine the type for you.
- The compiler looks at the code on the line of the declaration and uses it to infer the type.

```
public void reassignment(){
    var number = 7;
    number = 4;
    //number = "five"; // DOES NOT COMPILE. INCOMPATIBLE TYPE FOUND
}
```

- The type of var can't change at runtime, but what about the value?
- Take a look at the following code snipped:

```
public void promotedType(){
    var apples = (short)10;
    apples = (byte)5;
    //apples = 1_000_000; // DOES NOT COMPILE
}
```

- The last line does not compile, as one million is well beyond the limits of short.
- The compiler treats the value as an int and reports an error indicating it cannot be assigned to apples.

## Examples with var

- Do you think the following code compiles?

```
  public void doesThisCompile(boolean check){
      //var question;  // DOES NOT COMPILE
      //question = 1;
      //var answer;   // DOES NOT COMPILE
      if(check){
          //answer = 2;
      }else{
          //answer = 3;
      }
      //System.out.println(answer);
  }
```

- Can you figure out why these two statements don't compile?

```
4: public void twoTypes(){
5:     int a, var b = 3;  // DOES NOT COMPILE
6:     var n = null;      // DOES NOT COMPILE 
7: }
```

- Line 5 wouldn't work even if you replaced var with a real type.
- All the types declared on a single line must be the same type and share the same declaration.
- We couldn't write int a, int b = 3; either. Likewise, this is not allowed:

```
5: var a = 2, b = 3; // DOES NOT COMPILE
```

- In other words, Java does not allow var in multiple variable declarations.
- Line 6 is a single line. The compiler is being asked to infer the type of null.


---
### var and null ###

- While a var cannot be initialized with a null value without a type, it can be assigned a null value after it is 
  declared, provided that the underlying data type of the var is an object.
- Take a look at the following code snippet:

```
13: var n = "myData"
14: n = null;
15: var m = 4;
16: m = null; // DOES NOT COMPILE
```

- Line 14 compiles without issue because n is of type String, which is an object.
- On the other hand, line 16 does not compile since the type of m is a primitive int,
  which cannot be assinged a null value.


- It might surprise you to learn that a var can be initialized to a null value if the type
  is specified. The following code with a cast does compile:

```
17: var o = (String) null;
```

- Since the type is provided, the compiler can apply type inference and set the type of the var to be String.

---


- Do you see why this does not compile?

```
public int addition(var a, var b) { // DOES NOT COMPILE
  return a + b;
}
```

- Remember that var is used only for local variable type inference!

- Time for two more examples.

```java
package Appendix_A.type_reference;

public class Var {

    public void var(){
        var var = "var";
    }
    public void Var(){
        Var var = new Var();
    }
}
```

- Believe it or not, this code does compile.
- Naming a local variable var is legal.
- var is not a reserved word and allowed to be used as an identifier, it is considered a reserved type name.
- A reserved type name means it cannot be used to define a type, such as a class, interface, or enum.

```
public class var {  // DOES NOT COMPILE
  public var() {
  }
}
```

Style suggestion to use var: https://openjdk.org/projects/amber/guides/lvti-style-guide


## Review of var Rules

- Here's a quick review of the var rules:

1. A var is used as a local variable in a constructor, method, or initializer block.
2. A var cannot be used in constructor parameters, method parameters, instance variable, or class variables.
3. A var is always initialized on the same line (or statement) where it is declared.
4. The value of a var can change but the type cannot.
5. A var cannot be initialized with a null value without a type.
6. A var is not permitted in a multiple-variable declaration.
7. A var is a reserved type name but not a reserved word, meaning it can be used as an identifier except as a class, 
   interface, or enum name.

---
### Real World Scenario - var in the Real World ###

Once you start having code that looks like the following, it is time to consider using var:

```
PileOfPapersToFileInFilingCabinet pileOfPapersToFile = new PileOfPapersToFileInFilingCabinet();
```

You can see how shortening this would be an improvement without losing any information:

```
var pileOfPapersToFile = new PileOfPapersToFileInFilingCabinet();
```

---


## Introducing Modules

- The Java Platform Module System (JPMS) was introduced in Java 9 to group code at a higher level and tries to solve 
  the problems that Java has been plagued with since the beginning. The main propose of a module is to provide groups
  of related packages to offer a particular set of functionality to developers. It's like a JAR file except a developer
  choose which packages are accessible outside the module. Let's look at what modules are and what problems they are 
  designed to solve.
- The Java Platform Module System includes the following:
  - A format for module JAR files
  - Partitioning of the JDK into modules
  - Additional command-line options for Java tools


## Exploring a Module

- A module is a group of one packages plus a special file called module-info.java. Figure A.1 lists just a few of the
  modules a zoo might need. We decided to focus on the animal interactions in our example. The full zoo could easily
  have a dozen modules. In Figure A.1, notice that there are arrows between many of the modules. These represent 
  dependencies where one module relies on code in another. The staff needs to feed the animals to keep their jobs.
- The line from zoo.staff to zoo.animal.feeding shows the former depends on the latter.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_1.png?raw=true)


















































