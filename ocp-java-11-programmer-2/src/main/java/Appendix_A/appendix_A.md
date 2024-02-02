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


- Now let's drill down one of these modules. Figure A.2 shows what is inside the zoo.animal.talks module.
- There are three packages with two classes each. (It's a small zoo.) There is also a strange file called 
  module-info.java. This file is required to be inside all modules.
- We will explain this in more detail later.

## Benefits of Module

- Module look like another layer of things you need to know in order to program.
- While using modules is optional, it is important to understand the problems they are designed to solve

## Better Access Control

- There are four levels of access in a Java class file: private, package-private, protected, and public access.
- These levels of access control allow you to restrict access class or package. You could even allow access to 
  subclasses without exposing them to the world.
- However, what if we wrote some complex logic that we wanted to restrict to just some packages? For example, we would 
  like the packages in the zoo.animal.talks module to just be available to the packages in the zoo.staff module without
  making them available to any other code. Our traditional access modifiers cannot handle this scenario.
- Modules solve this problem by acting as a fifth level of access control. They can expose packages within the modular
  JAR to specific other packages. This stronger form of encapsulation really does create internal packages.


## Clearer Dependency Management

- It is common for libraries to depend on other libraries.
- For example, the JUnit 4 testing library depends on the Hamcrest library for matching logic.
- Developers would have to find this out by reading the documentation or files in the project itself.
- If you forgot to include Hamcrest in your classpath, your code would run fine until you used a Hamcrest class. Then
  it would blow up at runtime with a message about not finding a required class. (We did mention JAR hell, right?)
- In a fully modular environment, each of the open-source projects would specify their dependencies in the 
  module-info.java file. When launching the program, Java would complain that Hamcrest isn't in the module path, and 
  you'd know right away.


## Custom Java Builds

- The Java Development Kit (JDK) is larger than 150 MB. Even the Java Runtime Environment (JRE) was pretty big when it
  was available as a separate download. In the past, Java attempted to solve this with a compact profile. The three
  compact profiles provided a subset of the built-in Java classes so there would be a smaller package for mobile and 
  embedded devices.
- However, the compact profiles lacked flexibility. Many packages were included that developers were unlikely to use,
  such as Java Native Interface (JNI), which is for working with OS-specific programs. At the same time, using other
  packages like Image I/O required the full JRE.
- The Java Platform Module System allows developers to specify what modules they actually need. This makes it possible 
  to create a smaller runtime image that is customized to what the application needs and nothing more. Users can run
  that image without having Java installed at all.
- A tool called jlink is used to create this runtime image.
- In addition to the smaller-scale package, this approach improves security. If you don't use AWT and a security 
  vulnerability is reported for AWT, applications that packaged a runtime image without AWT aren't affected.


## Improved Performance

- Since Java now knows which modules are required, it only needs to look at those at class loading time.
- This improves startup time for big programs and requires less memory to run.


## Unique Package Enforcement

- Another manifestation of JAR hell is when the same package is in two JARs.
- The Java Platform Module System prevents this scenario. A package is allowed to be supplied by only one module.
- No more unpleasant surprises about a package at runtime.


---
### Modules for Existing Code ###

While not all open source projects have switched over, more than 4,000 have. There's a list of all Java modules on 
GitHub at https://github.com/sormuras/modules/blob/master/README.md
---

## Creating and Running a Modular Program

- In this section, we will create, build, and run the zoo.animal.feeding module. 
- We choose this one to start with because all the other modules depend on it. 
- Figure A.3 shows the desing of this module. In addition to the module-info.java file, it has one package with one 
  class inside.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_3.png?raw=true)


- IN the next sections, we will create, compile, run, and package the zoo.animal.feeding module.


## Creating the Files

- First we have a really simple class that prints one line in a main() method.

```java
package Appendix_A.modules;

public class Task {
    public static void main(String... args) {
        System.out.println("All fed!");
    }
}
```

- Next comes the module-info.java file. This is the simplest possible one.

```
module zoo.animal.feeding {
}
```

- There are a few keys differences between a module-info.java file and a regular class.
  - The module-info file must be in the root directory of your module. Regular Java classes should be in packages
  - The module-info file must use the keyword module instead of class, interface, or enum.
  - The module name follows the naming rules for packages names.
    It often includes periods (.) in its name. Regular class and package names are not allowed to have dashes (-).
    Module names follow the same rule.


---
### Can a module-info.java File Be Empty?
Yes.
---

- Figure A.4 shows the expected directory structure.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_4.png?raw=true)


- In particular, feeding is the module directory, and the module-info file is directly under it.
- Just as with a regular JAR file, we also have the ***zoo.animal.feeding*** package with one subfolder per portion of the name.
- The Task class is in the appropriate subfolder for its package.
- Also, note that we created a directory called ***mods***  at the same level as the module.
- We will use it for storing the module artificats a little later in the appendix.
- This directory can be named anything, but ***mods*** is a common name.
- If you are following along with the online code example, note that the ***mods*** directory is not included, because 
  it is empty.

Note: https://github.com/boyarsky/sybex-1Z0-815-chapter-11

## Compiling Our First Module

- Before we can run modular code, we need to compile it. Other than the ***module-path*** options, this code  look 
  familiar:


```shell
javac --module-path mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java
```

- As a review, the -d option specifies the directory to place the class files in.
- The end of the command is a list of the .java files to compile.
- You can list the files individually or use wildcard for all .java files in a subdirectory.
- The new part is the module-path. This option indicates the location of any custom module files.
- In this example, ***module-path*** could have been omitted since there are no dependencies.
- You can think of ***module-path*** as replacing the ***classpath*** option when you are working on a modular program.



---
### What happend to the Classpath? ###

- In the past, you would reference JAR file using the classpath option. It had three possibles forms: -cp, --class-path,
  and -classpath
- You can still use these options in Java 11. 
- In fact, it is common to do so when writing non-modular programs
---


- The abbreviation for --module-path is -p
- The following four commands show the -p option:

```shell
javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/*.java

javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java

javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/module-info.java

javac -p mods -d feeding feeding/zoo/animal/feeding/Task.java feeding/*.java
```

- Table A.1 lists the options you need to know well when compiling modules
  - There are many options you can pass to the javac command, but these are the ones you can expect to be tested on.

| User for      | Abbreviation | Long form              |
|---------------|--------------|------------------------|
| Module path   | -p <path>    | --module-path <path>   |


## Running Our First Module

- Before we package our module, we should make sure it works by running it.
- Suppose there is a module named *book.module*. Inside that module is a package named *com.sybex*, which has a class
  named OCP with a main() method.
- *Figure A.5* shows the syntax for running a module. Pay special attention to the *book.module/com.sybex.OCP* part.
- It is important to remember that you specify the module name followed by a slash (/) followed by the fully qualified name.


























