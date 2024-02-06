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

| User for                  | Abbreviation | Long form            |
|---------------------------|--------------|----------------------|
| Directory for class files | -d <dir>     | n/a                  |
| Module path               | -p <path>    | --module-path <path> |


## Running Our First Module

- Before we package our module, we should make sure it works by running it.
- Suppose there is a module named *book.module*. Inside that module is a package named *com.sybex*, which has a class
  named OCP with a main() method.
- *Figure A.5* shows the syntax for running a module. Pay special attention to the *book.module/com.sybex.OCP* part.
- It is important to remember that you specify the module name followed by a slash (/) followed by the fully qualified name.



![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_5.png?raw=true)



```shell
java --module-path feeding --module zoo.animal.feeding/zoo.animal.feeding.Task
```

- Since you already saw that --module-path uses the short form of -p, we bet you won't be surprised to learn there is a 
  short form of --module as well.
- The short option is -m. That means the following command equivalent:

```shell
java -p feeding -m zoo.animal.feeding/zoo.animal.feeding.Task
```

- In this examples, we used feeding as the module path because that's where we compiled the code.
- This will change once we package the module and run that.


---
### TABLE A.2 Options you need to know for using modules with java

| User for      | Abbreviation | Long form            |
|---------------|--------------|----------------------|
| Module name   | -m <name>    | --module <name>      |
| Module path   | -p <path>    | --module-path <path> |

---

## Packing Our First Module

- Our next step is to package it. Be sure to create a *mods* directory before running this command.

```shell
jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .
```

- There's nothing module-specific here.
- We are packing everything under the *feeding* directory and storing in a JAR file named *zoo.animal.feeding.jar* under
  the mods folder.
- This represents how the module JAR will look to other code that wants to use it.


> Note
> It is possible to version your module using the --module-version option.
> This isn't on the exam but is good to do when you are ready to share your module with others.


- Now let's run the program again, 
- but this time using the *mods* directory instead of the loose classes.

```shell
java -p mods -m zoo.animal.feeding/zoo.animal.feeding.Task
```

- You might notice that this command looks identical to the one in the previous section except for the directory.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_6.png?raw=true)


## Updating Our Example for Multiple Modules

- Now that our *zoo.animal.feeding* module is solid, we can start thinking about our other modules.
- As you can see in ***Figure A.7***, all three of the other modules in our system depend on the zoo.animal.feeding module.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_7.png?raw=true)


## Updating the Feeding Module

- Since we will be having our other modules call code in the zoo.animal.feeding package, we need to declare this intent
  in the module-info file.
- The exports keyword is used to indicate that a module intents for those packages to be used by Java code outside the
  module. 
- As you might expect, without an exports keyword, the module is only available to be run from the command line
  on its own. 
- In the following example, we export one package:

```java
module zoo.animal.feeding{
    exports zoo.animal.feeding;
}
```

- Recompiling and repackaging the module will update the *module-info* inside our *zoo.animals.feeding.jar* file.
- These are the same *javac* and *jar* commands you run previously.

```shell
javac -p mods -d feeding feeding/zoo/animal/feeding/*.java feeding/module-info.java

jar -cvf mods/zoo.animal.feeding.jar -C feeding/ .
```

## Creating a Care Module

- Next, let's create the zoo.animal.care module.
- This time, we are going to have two packages. 
- The *zoo.animal.care.medical* package will have the classes and methods that are intended for use by other modules.
- The *zoo.animal.care.details* package is only going to be used by this module. It will not be exported from the module.
- Think of it as healthcare privacy for the animals.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_8.png?raw=true)


- The module contains two basic packages and classes in addition to the *module-info.java*

```java
package zoo.animal.care.details;

import zoo.animal.feeding.*;

public class HippoBirthday {
    private Task task;
}
```
```java
package zoo.animal.care.medical;

public class Diet {
}
```
```
1:  module zoo.animal.care {
2:    exports zoo.animal.care.medical;
3:    requires zoo.animal.feeding;
4:  }
```

- Line 1 specifies the name of the module.
- Line 2 lists the package we are exporting so it can be used by other modules.
- So far, this is similar to the *zoo.animal.feeding* module.
- On line 3, we see a new keyword. The *requires* statement specifies that a module is needed.
- The *zoo.animal.care* module depends on the *zoo.animal.feeding* module.


- Figure A.9 shows the directory structure of this module. Note that *module-info.java* is in the root of the module.
- The two packages are underneath it.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_9.png?raw=true)


- You might have noticed that the packages begin with the same prefix as the module name.
- This is intentional. You can think of it as if the module name "claims" the matching package and all subpackages.
- To review, we now compile and package the module.

```shell
javac -p mods -d care care/zoo/animal/care/details/*.java care/zoo/animal/care/medical/*.java care/module-info.java
```

- We compile both packages and the module-info file.
- In the real world, you'll use a build too rather than doing this by hand.
- For the exam, you just list all the packages and/or files you want to compile.

---
### Order May Matter! ###


Note that order matter when compiling a module. Suppose we list the module-info file first when trying to compile.

```shell
javac -p mods -d care are/module-info.java \ 
         care/zoo/animal/care/details/*.java \  
         care/zoo/animal/care/medical/*.java 
```

- The compiler complains that it doesn't know anything about the package *zoo.animal.care.medical*.

```
care/module-info.java:3: error package is empty
  or does not exist: zoo.animal.care.medical
exports zoo.animal.care.medical;
```

- A package must have at least one class in it to be exported. Since we haven't yet compiled *zoo.animal.care.medical.Diet*,
- the compiler acts as if it doesn't exist.
- If you get this error message, you can reorder the *javac* statement.
- Alternatively, you can compile the packages in a separate *javac* command, before compiling the module-info file.
---


- Now that we have compiled code, it's time to create the module JAR.

```shell
jar -cvf mods/zoo.animal.care.jar -C care/ .
```


## Creating the Talks Module

- So far, we've used only one *exports* and *requires* statement in a module.
- Now you'll learn how to handle exporting multiple packages or requiring multiple modules.
- In Figure A.10, observe that the *zoo.animal.care* 
- This means that there must be two requires statements in *module-info.java* file.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_10.png?raw=true)

- Figure A.11 shows the contents of this module. We are going to export all three packages in this module.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_11.png?raw=true)

- First let's look at the *module-info.java* file for *zoo.animal.talks*.

```
1: module zoo.animal.talks {
2:  exports zoo.animal.talks.content;
3:  exports zoo.animal.talks.media;
4:  exports zoo.animal.talks.schedule;
5:
6:  requires zoo.animal.feeding;
7:  requires zoo.animal.care;
8: }
```


- Line 1 shows the module name.
- Line 2-4 allow other modules to reference all three packages.
- Line 6-7 specify the two modules that this modules depends on.


```java
package zoo.animal.talks.content;

public class ElephantScript {
}
```

```java
package zoo.animal.talks.content;

public class SeaLionScript {
}
```

```java
package zoo.animal.talks.media;

public class Announcement {
    public static void main(String[] args) {
        System.out.println("We will be having talks");
    }
}
```

```java
package zoo.animal.talks.media;

public class Signage {
}
```

```java
package zoo.animal.talks.schedule;

public class Weekday {
}
```

```java
package zoo.animal.talks.schedule;

public class Weekend {
}
```

- The following are the commands to compile and build the module:

```shell
javac -p mods -d talks talks/zoo/animal/talks/content/*.java talks/zoo/animal/talks/media/*.java talks/zoo/animal/talks/schedule/*.java talks/module-info.java   
              
jar -cvf mods/zoo.animal.talks.jar -C talks/ .
```

## Creating the Staff Module

- Our final module is zoo.staff. 
- Figure A.12 shows there is only one package inside.
- We will not be exposing this package outside the module.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_12.png?raw=true)


```java
module zoo.staff {

    requires zoo.animal.feeding;
    requires zoo.animal.care;
    requires zoo.animal.talks;
}
```

- There are three arrows in Figure A.13 pointing from *zoo.staff* to other modules.
- These represents the three modules that are required. 
- Since no packages are to be exposed from zoo.staff, there are no *exports* statements.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/images/Figure_A_13.png?raw=true)


- In this module we have a single class in file *Jobs.java*.

```java
package zoo.staff;

public class Jobs {
}
```

- The following are the commands to compile and build the module:

```shell
javac -p mods -d staff staff/zoo/staff/*.java staff/module-info.java

jar -cvf mods/zoo.staff.jar -C staff/ .
```

## Diving into the module-info File

- Now that we've successfully created modules, we can talk more about the *module-info* file.
- We will look at *exports, requires, provides, uses*, and *opens*.
- These keywords can appear in any order in the *module-info* file.


## exports

- We've already seen how *exports packageName* exports a package to other modules.
- It's also possible to export a package to a specific module.
- Suppose that only staff members should have access to the talks.
- We could update the module declaration as follows:

```java
module zoo.animal.talks {
    exports zoo.animal.talks.content to zoo.staff;
    exports zoo.animal.talks.media;
    exports zoo.animal.talks.schedule;

    requires zoo.animal.feeding;
    requires zoo.animal.care;
}
```

- From the zoo.staff module, nothing has changed. However, no other modules would be allowed to access that package.

- The *exports* keyword essentially gives us more levels of access control. Table A.3 lists the full access control 
  options.

---
### TABLE A.3 Access control with modules

| Level                    | Withing module code                            | Outside module                            |
|--------------------------|------------------------------------------------|-------------------------------------------|
| private                  | Available only within class                    | No access                                 |
| default (package-private | Available only within package                  | No access                                 |
| protected                | Available only within package or to subclasses | Accessible to subclasses only if exported |
| public                   | Available to all classes                       | Accessible only if package is exported    |
---


## requires transitive

