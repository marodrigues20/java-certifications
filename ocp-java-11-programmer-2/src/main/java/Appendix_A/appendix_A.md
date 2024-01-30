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



