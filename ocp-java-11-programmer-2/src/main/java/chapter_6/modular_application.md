# Chapter 6 - Modular Application

- Migration to a Modular Application
  - Migrate the application developed using a Java version prior to SE 9 to SE 11 including top-down and bottom-up
    migration, splitting a Java SE8 application into modules for migration.
  - Use jdeps to determine dependencies and identify ways to address the cyclic dependencies
- Services in a Modular Application
  - Describe the components of Services including directives
  - Design a service type, load services using ServiceLoader, check for dependencies of the services including consumer 
    provider modules.


- If you took the 1ZO-815 exam, you learned the basics of modules. If not, read Appendix A, "The Upgrade Exam", before
  reading this chapter.
- This chapter covers strategies for migrating an application to use modules, running a partially modularized application,
  and dealing with dependencies.
- We then move on to discuss services and services locators.
- Feel free to use the files we've already set up in the GitHub repo linked to from https://www.selikoff.net/ocp11-2/


## Reviewing Module Directive

- If anything in Table 6.1 is unclear or unfamiliar, please stop and read 
  [Appendix A](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/Appendix_A/appendix_A.md) 
  before continuing in this chapter.

---
### TABLE 6.1 Common module directives

| Derivative                        | Description                                                                                    |
|-----------------------------------|------------------------------------------------------------------------------------------------|
| exports <package>                 | Allows all modules to access the package                                                       |
| exports <package> to <module>     | Allows a specific module to access the package                                                 |
| requires <module>                 | Indicates module is dependent on another module                                                |
| requires transitive <module>      | Indicates the module and that all modules that use this module are dependent on another module |
| uses <interface>                  | Indicates that a module uses a service                                                         |
| provides <interface> with <class> | Indicates that a module provides an implementation of a service                                |

---

> If you don't have any experience with *uses* or *provides*, don't worry - They will be covered in this chapter.


## Comparing Types of Modules

- The modules you learned about for the 1ZO-815 exam (or in Appendix A) are called *named modules* 
- There are two other types of modules: automatic modules and unnamed module.
- In this section, we describe these types of modules
- On the exam, you will need to be able to compare them.



## Named Modules

- A *named module* is one containing a *module-info* file.
- To review, this file appears in the root of the JAR alongside one or more packages. Unless otherwise specified, a 
  module is a named module. Named modules appear on the module path rather than the classpath.
- For now, just know it is not considered a named module because it is not on the module path.

### Remember

- A named module has the name inside  the *module-info* file and is on the module path.
- Figure 6.1 shows the contents of a JAR file for a named module. It contains two packages in addition to the 
  *module-info.class*

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_1.png?raw=true)

## Automatic Modules

- An *automatic module* appears on the module path but does not contain a *module-info* file. It is simply a regular
  JAR file that is placed on the module path and gets treated as a module.
- As a way of remembering this, Java automatically determines the module name. Figures 6.2 shows an automatic module with
  two packages.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_2.png?raw=true)


---
### Real World Scenario ###

- About the Manifest

- A JAR file is a zip file with a special directory named *META-INFO*. This directory contains one or more files.
- The *MANIFEST.MF* file is always present. 
- The figure shows how the manifest fits into the directory structure of a JAR file.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_Real_World_Scenario_1.png?raw=true)


- The manifest contains extra information about the JAR file. For example, it often contains the version of Java used to 
  build the JAR file.
- For command-line programs, the class with the main() method is commonly specified.
- Each line in the manifest is a key/value pair separated by a colon. You can think of the manifest as a map of property
  names and values.

  ```
  Manifest-Version: 1.0"
  Created-By: 11.0.2 (Oracle Comporate)
  ```
---

- The code referencing an automatic module treats it as if there is a *module-info* file present.
- It automatically exports all packages. It also determines the module name.

## How Java determine the module name?

- When Java 9 was released, authors of Java libraries were encouraged to declare the name they intended to use for the 
  module in the feature. All they had to do was set a property called *Automatic-Module-Name* in the *MANIFEST.MF* file.
- If the JAR file does not specify an automatic module name, Java will still allow you to use it in the module path.
  In this case, Java will determine the module name for you. We'd say that this happens automatically.
- Java determines the automatic module name by basing it off the filename of the JAR file.
- Let's go over the rules by starting with an example:
  - Supposing we have a JAR file named *holiday-calendar-1.0.0.jar.
    - Java will remove:
      - .jar extension
      - version
      - Java convert dashes(-) or any special characters in the name of dots(.)
      - Any adjacent dots or leading/trailing dots are removed.
    - As a result: *holiday.calendar*


- Table 6.2 shows how to apply these rules to two examples where there is no automatic module name specified in the 
  manifest.


| #   | Description                                                        | Example 1                     | Example 2     |
|-----|--------------------------------------------------------------------|-------------------------------|---------------|
| 1   | Beginning JAR name                                                 | commons2-x-1.0.0-SNAPSHOT.jar | mod_$-1.0.jar |
| 2   | Remove file extension                                              | commons2-x-1.0.0-SNAPSHOT     | mod_$-1.0     |
| 3   | Remove version information                                         | commons2-x                    | mod_$         |
| 4   | Replace special characters                                         | commons2.x                    | mod..         |
| 5   | Replace sequence of dots                                           | commons2.x                    | mod.          |
| 6   | Remove leading/trailing dots(results in the automatic module name) | commons2.x                    | mod           |


- While the algorithm for creating automatic module names does its best, it can't always come up with a good name.
- For example, 1.2.0-calendar-1.2.2-good-1.jar isn't conductive. Luckily such names are rare and out of scope for the exam.


## Unnamed Modules

- An *unnamed module* appears on the classpath. Like an automatic module, it is a regular jAR.
- Unlike an automatic module, it is on the classpath rather than the module path.
- This means an unnamed module is treated like old code and second-class citizen to modules.
- Figure 6.3 shows an unnamed module with one package.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_3.png?raw=true)


- An unnamed module does not usually contain a *module-info* file. If it happens to contain one, that file will be 
  ignored since it is on the classpath.
- Unnamed module do not export any package to named or automatic modules.
- The unnamed module can read from any JARs on the classpath or module path.
- You can think of an unnamed module as code that works the way Java worked before modules.


## Comparing Module Types

- You can expect to get questions on the exam comparing the three types of modules.
- Please study Table 6.3 thoroughly an be prepared to answer questions about these items in any combination.
- A key point to remember is that code on the classpath can access the module path. By contrast, code on the module
  path is unable to read from the classpath.



---
### TABLE 6.3 Properties of module types ###

| Property                                                      | Named                           | Automatic    | Unnamed            |
|---------------------------------------------------------------|---------------------------------|--------------|--------------------|
| A____ module contains a *module-info* file?                   | Yes                             | No           | Ignored if present |
| A____ module exports which packages to other modules?         | Those in the *module-info* file | All packages | No packages        |
| A____ module is readable by other modules on the module path? | Yes                             | Yes          | No                 |
| A____ module is readable by other jARs on the classpath?      | Yes                             | Yes          | Yes                |
---


## Analyzing JDK Dependencies

- In this part of the chapter, we look at modules that are supplied by the JDK. We also look at the *jdeps* command
  for identifying such module dependencies.

### Identifying Built-in Modules

- Prior to Java 9, developers could use any package in the JDK by merely importing it into the application.
- This meant the whole JDK had to be available at runtime because a program could potentially need anything.
- With modules, your application specifies which parts of the JDK it uses. This allows the application to run on a full
  JDK or a subset.
- You might be wondering what happens if you try to run an application that references a package that isn't available
  in the subset. No worries! The *requires* directive in the *module-info* file specifies which modules need to be 
  present at both compile time and runtime. This means they are guaranteed to be available for the application to run.
- The most important module to know is *java.base*. It contains most of the packages you have learning about for the
  exam.In fact, it is so important that you don't even have to use the *requires* directive; it is available to all 
  modular applications. Your *module-info.java* file will still compile if you explicitly require *java.base*. However,
  it is redundant, so it's better to omit it.


---
### Table 6.4 lists some common modules and what they contain. ###


| Module name  | What it contains                               | Coverage in book                       |
|--------------|------------------------------------------------|----------------------------------------|
| java.base    | Collections, Math, IO, NIO2, Concurrency, etc. | Most of this book                      |
| java.desktop | Abstract Windows Toolkit (AWS) and Swing       | Not on the exam beyond the module name |
| java.logging | Logging                                        | Not on the exam beyond the module name | 
| java.sql     | JDBC                                           | Chapter 10, "JDBC"                     |
| java.xml     | Extensible Markup Language (XML)               | Not on the exam beyond the module name |

- You need to know the names of modules supplied by JDK.
- You don't need to know the names by hear, you do need to be able to pick them out of a lineup.

---

---
### TABLE 6.5 Java modules prefixed with java ###

| java.base           | java.naming        | java.smartcardio    |
|---------------------|--------------------|---------------------|
| java.compiler       | java.net.http      | java.sql            |
| java.datatransfer   | java.prefs         | java.sql.rowset     |
| java.desktop        | java.rmi           | java.transaction.xa |
| java.instrument     | java.scripting     | java.xml            |
| java.logging        | java.se            | java.xml.crypto     |
| java.management     | java.security.jgss | -                   | 
| java.management.rmi | java.security.sasl | -                   |

---

---
### TABLE 6.6 Java modules prefixed with jdk ###

| jdk.accessiblity    | jdk.jconsole         | jdk.naming.dns        |
|---------------------|----------------------|-----------------------|
| jdk.attach          | jdk.jdeps            | jdk.naming.rmi        |
| jdk.charsets        | jdk.jdi              | jdk.net               |
| jdk.compilers       | jdk.jdwp.agent       | jdk.pack              |
| jdk.crypto.cryptoki | jdk.jfr              | jdk.rmic              |
| jdk.crypto.ec       | jdk.jlink            | jdk.scripting.nashorn |
| jdk.dynalink        | jdk.shell            | jdk.sctp              |
| jdk.editpad         | jdk.jsobject         | jdk.security.auth     |
| jdk.hotspot.agent   | jdk.jstatd           | jdk.security.jgss     |
| jdk.httpserver      | jdk.localdata        | jdk.xml.dom           |
| jdk.jartool         | jdk.management       |  jdk.zipfs            |
| jdk.javadoc         | jdk.management.agent | -                     |
| jdk.jcmd            | jdk.management.jfr   | -                     |
---


## Using jdeps

- The *jdeps* command gives you information about dependencies.
- Luckily, you are not expected to memorize all the options for the 1ZO-816 exam.
- You are expected to understand how to use *jdeps* with projects that have not yet been modularized to assist in 
  identifying dependencies and problems.
- First, we will create a JAR file from this class.


```java
package zoo.dinos;

import java.time.*;
import java.util.*;

import sun.misc.Unsafe;

public class Animatronic {

    private List<String> names;
    private LocalDate visitDate;

    public Animatronic(List<String> names, LocalDate visitDate) {
        this.names = names;
        this.visitDate = visitDate;
    }

    public void unsafeMethod() {
        Unsafe unsafe = Unsafe.getUnsafe();
    }
}

```

- We can compile this file.
- You might have noticed there is no *module-info.java* file.
- That is because we are not creating a module. 
- We are looking into what dependencies we will need when we do modularize this JAR.

```shell
javac zoo/dinos/*.java
```
- This is the out put

```shell
zoo/dinos/Animatronic.java:6: warning: Unsafe is internal proprietary API and may be removed in a future release
import sun.misc.Unsafe;
               ^
zoo/dinos/Animatronic.java:19: warning: Unsafe is internal proprietary API and may be removed in a future release
        Unsafe unsafe = Unsafe.getUnsafe();
        ^
zoo/dinos/Animatronic.java:19: warning: Unsafe is internal proprietary API and may be removed in a future release
        Unsafe unsafe = Unsafe.getUnsafe();
                        ^
3 warnings
```

- Compiling works, but it gives you some warnings about *Unsafe* being an internal API.
- Don't worry about those for now - We'll discuss that shortly.

```shell
jar -cvf zoo.dino.jar .
```

```shell
added manifest
adding: images/(in = 0) (out= 0)(stored 0%)
adding: images/Figure_6_1.png(in = 119890) (out= 102319)(deflated 14%)
adding: images/Figure_6_2.png(in = 98668) (out= 80609)(deflated 18%)
adding: images/Figure_6_3.png(in = 76643) (out= 59278)(deflated 22%)
adding: images/Figure_Real_World_Scenario_1.png(in = 92974) (out= 76520)(deflated 17%)
adding: modular_application.md(in = 15704) (out= 5056)(deflated 67%)
adding: zoo/(in = 0) (out= 0)(stored 0%)
adding: zoo/dinos/(in = 0) (out= 0)(stored 0%)
adding: zoo/dinos/Animatronic.class(in = 637) (out= 389)(deflated 38%)
adding: zoo/dinos/Animatronic.java(in = 413) (out= 209)(deflated 49%)
```

- We can run the *jdeps* command against this JAR to learn about its dependencies.
- First, let's run the command without any options. On the first two lines, the command prints the modules that we would 
  need to add with a *requires* directive to migrate to the module system. 
- It also prints a table showing what packages are used and what modules they correspond to.

```shell
jdeps zoo.dino.jar
```
- Output

```shell
zoo.dino.jar -> java.base
zoo.dino.jar -> jdk.unsupported
   chapter_6.zoo.dinos                                -> java.lang                                          java.base
   chapter_6.zoo.dinos                                -> java.time                                          java.base
   chapter_6.zoo.dinos                                -> java.util                                          java.base
   chapter_6.zoo.dinos                                -> sun.misc                                           JDK internal API (jdk.unsupported)
```

- If we run in summary mode, we only see just the first part where *jdeps* lists the modules.

```shell
jdeps -s zoo.dino.jar
```
- Output
```shell
zoo.dino.jar -> java.base
zoo.dino.jar -> jdk.unsupported
```

- For a real project, the dependency list could dozens or even hundreds of packages.
- It's useful to see the summary of just the modules.
- This approach also makes it easier to see whether *jdk.unsupported* in the list.

> Note:
> You might have noticed the *jdk.unsupported* is not in the list of modules you saw in
> Table 6.6. It's special because it contains internal libraries that developers in previous versions of Java were
> discouraged from using, although many people ignored this warning. You should not reference it as it may disappear
> in future version of Java.


- The *jdeps* command has an option to provide details about these unsupported APIs.
- The output looks something like this:

```shell
jdeps --jdk-internals zoo.dino.jar
```

- Output 

```shell
zoo.dino.jar -> jdk.unsupported
   chapter_6.zoo.dinos.Animatronic                    -> sun.misc.Unsafe                                    JDK internal API (jdk.unsupported)

Warning: JDK internal APIs are unsupported and private to JDK implementation that are
subject to be removed or changed incompatibly and could break your application.
Please modify your code to eliminate dependence on any JDK internal APIs.
For the most recent update on JDK internal API replacements, please check:
https://wiki.openjdk.java.net/display/JDK8/Java+Dependency+Analysis+Tool

JDK Internal API                         Suggested Replacement
----------------                         ---------------------
sun.misc.Unsafe                          See http://openjdk.java.net/jeps/260
```

- Note that -jdkinternals is equivalent to --jdk-internals.

## Migrating an Application

- All applications developed for Java 8 and earlier were not designed to use Java Platform Module System because it did 
  not exist yet.
- Ideally, there were at least designed with projects instead of as a big ball of mud.
- This section will give you an overview of strategies for migrating an existing application to use modules.
- We will cover ordering modules, bottom-up migration, top-down migration, and how to split up an existing project.


---
### Real World Scenario ###

- Migrating Your Applications at Work

- The exam exists in a pretend universe where there are no open-source dependencies and applications are very small.
- These scenarios make learning and discussing migration far easier.
- In the real world, applications have libraries that haven't been updated in 10 or more years, complex dependency
  graphs, and all sorts of surprises.
<br>
- Note that you can use all the features of Java 11 without converting your application to modules (except the features
  in this module chapter, of course!. Please make sure you have a reason for migrating and don't think it is required).
- If you need to be prepared to migrate real applications to use modules, consider reading *The Java Module System by 
  Nicolai Parlog (Manning PUblications, 2019).
---


### Determining the Order

- Before we can migrate our application to use modules, we need to know the packages and libraries in the existing 
  application are structured. Suppose we have a simple application with three JAR files, as shown in Figure 6.4. The
  dependencies between projects form a graph. Both of the representation in Figure 6.4 are equivalent. 
- The arrows show the dependencies by pointing from the project that will require the dependency to the one that makes 
  it available.
- In the language of modules, the arrow will go from *requires* to the *exports*



![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_4.png?raw=true)


- The right side of the diagram makes it easier to identify the top and bottom that top-down and bottom-up migration
  refer to. Projects that do not have any dependencies are at the bottom. Projects that do have dependencies are at the 
  top.
- In this example, there is only one order from top to bottom that honors all the dependencies. Figure 6.5 shows that 
  the order is not always unique. Since two of the project do not have an arrow between them, either order is allowed
  when deciding migration order.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_5.png?raw=true)


## Exploring a Bottom-up Migration Strategy

- The easiest approach to migration is a bottom-up migration. 
- This approach works best when you have the power to convert any JAR files that aren't already modules.
- For a bottom-up migration, you follow these steps:
  1. Pick the lowest-level project that has not yet been migrated. (Remember the way we ordered them by dependencies in 
     the previous section?)
  2. Add a *module-info.java* file to that project. Be sure to add any *exports* to expose any package used by 
     higher-level JAR files. Also, add a *requires* directive for any modules it depends on.
  3. Move this newly migrated named module from the classpath to the module path.
  4. Ensure any project that have not yet been migrated stay as unnamed modules on the classpath.
  5. Repeat with the next-lowest-level project until you are done.


- You can see this procedure applied to migrate three projects in Figure 6.6.
- Notice that each project is converted to a module it turn.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_6.png?raw=true)

- During migration, you have a mix of named modules and unnamed modules.
- The named modules are the lower-level ones that have been migrated.
- They are on the module path and not allowed to access any unnamed modules.
- The unnamed modules are on the classpath. They can access JAR files on both the classpath and the module path.


## Exploring a Top-Down Migration Strategy


- A top-down migration strategy is most useful when you don't have control of every jAR file used by your application.
- For example, suppose another team owns one project. They are just too busy to migrate.
- For a top-down migration, you follow these steps:
  1. Place all projects on the module path.
  2. Pick the highest-level project that has not yet been migrated.
  3. Add a *module-info* file to that project to convert the automatic module into a named module. Again, remember to 
     add any *exports* or *requires* directives. You can use the automatic module name of other modules when writing
     the *requires* directive since most of the projects on the module path do not have names yet.
  4. Repeat with the next-lowest-level project until you are done.

- You can see this procedure applied in order to migrate three projects in Figure 6.7. 
- Notice that each project is converted to a module in turn. 


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_7.png?raw=true)


- With a top-down migration, you are conceding that all of the lower-level dependencies are not ready but want to make 
  the application itself a module.
- During migration, you have a mix of named modules and automatic modules. The named are the higher-level ones that have
  benn migrated. They are on the module path and have access to the automatic modules. The automatic modules are also on
  the module path.


---
### Comparing migration strategies ###

| Category                             | Bottom-up                       | Top-Down                            |
|--------------------------------------|---------------------------------|-------------------------------------|
| A project that depends on all others | Unnamed module on the classpath | Named module on the module path     |
| A project that has no dependencies   | Named module on the module path | Automatic module on the module path |
---


## Splitting a Big Project into Modules

***For the exam, you need to understand the basic process of splitting up a big project into modules.***


- Suppose you start with an application that has a number of packages. 
- The first step is to break them up into logical groupings and draw the dependencies between them.
- Figure 6.8 shows an imaginary system's decomposition.
- Notice that there are seven packages on both the left and right side.
- There are fewer modules because some packages share a module.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_8.png?raw=true)

- There's a problem with this decomposition.
- The Java Platform Module System does not allow for *cyclic dependencies*.
- A cyclic dependencies, or *circular dependencies*, is when two things directly or indirectly depend on each other.

- If the *zoo.tickets.delivery* module requires the *zoo.tickets.discount* module, the *zoo.tickets.discount* is not 
  allowed to require the *zoo.tickets.delivery* module.
- A common technique to solve this issue is to introduce another module.
- That module contains the code that the other two modules share.
- Figure 6.9 shows the new modules without any cyclic dependencies.
- Notice the new module *zoo.tickets.discount*.
- We created a new package to put in that module. This allows the developers to put the common code in there and break
  the dependencies. No more cyclic dependencies!


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_9.png?raw=true)


## Failing to Compile with a Cyclic Dependency

- Java will now allow you to compile modules that have circular dependencies between each other.
- We will look at an example leading to that compiler error.
- First, let's create a module named *zoo.butterfly* that has a single class in addition to the *module-info.java* file.
- If yo need a reminder where the files go in the directory, see Appendix A or the online code example.


- Check code here: https://github.com/boyarsky/sybex-1Z0-816-chapter-6/tree/master/cyclic-dependencies
```java
package zoo.butterfly;

public class Butterfly {
}
```

```java
module zoo.butterfly {
    exports zoo.butterfly;

}
```

- We can compile butterfly module and create a JAR file in the *mods* directory named *zoo.butterfly.jar*.
- Remember to create a *mods* directory if one doesn't exist in your folder structure.

```shell

javac -d butterflyModule
  butterflyModule/zoo/butterfly/Butterfly.java
  butterflyModule/module-info.java
```

```shell
jar -cvf mods/zoo.butterfly.jar -C butterflyModule/ .
```

- Now we create a new module, *zoo.caterpillar*, that depends on the existing *zoo.butterfly* module.
- This time, we will create a module with two classes in addition to the *module-info.java* file.

```java
package zoo.caterpillar;

public class Caterpillar {

}
```

```java
package zoo.caterpillar;

import zoo.butterfly.Butterfly;

public interface CaterpillarLifecycle {

    Butterfly emergeCocoon();
}
```

```java
module zoo.caterpillar {

    requires zoo.butterfly;
}
```

- Again, we will compile and create a JAR file. This time it is named *zoo.caterpillar.jar*.


```shell
javac -p mods -d caterpillarModule
  caterpillarModule/zoo/caterpillar/*.java
  caterpillarModule/module-info.java
```

```shell
jar -cvf mods/zoo.caterpillar.jar -C caterpillarModule/ .
```

- At this point, we want to add a method for a butterfly to make caterpillar eggs.
- We decide to put it in the *Butterfly* module instead of the *CaterpillarLifecycle* class to demonstrate a 
  cyclic dependency.
- We know this requires adding a dependency, so we do that first. Updating the *module-info.java* file in the 
  *zoo.butterfly* module looks like this:

```java
module zoo.butterfly {
    exports zoo.butterfly;
    // uncomment to introduce circular dependency
    requires zoo.caterpillar;
}
```

- We then compile it with the module path *mods* so *zoo.caterpillar* is visible:

````shell

javac -p mods -d butterflyModule
  butterflyModule/zoo/butterfly/Butterfly.java
  butterflyModule/module-info.java
````

- The compiler complains about our cyclic dependency.

```shell
    butterflyModule/module-info.java:3: error:     
      cyclic dependence involving zoo.caterpillar       
        requires zoo.caterpillar;
```

- This is one the advantages of the module system.
- It prevents you from writing code that has cyclic dependency.
- Such code won't even compile!

> Tip
> Java will still allow you to have a cyclic dependency between packages within a module.
> It enforces that you do not have a cyclic dependency between modules


## Creating a Service

- A *service* is composed of an interface, any classes the interface references, and a way of looking up implementations
  of the interface.
- The implementation are not part of the service.

- Services are not new to Java.
- In fact, the *ServiceLoader* class was introduced in Java 6. It was used to make applications *extensible*, so you 
  could add functionality without recompiling the whole application.
- What is new in the integration with modules.

- We will be using a tour application in the services section.
- It has four modules shown in Figure 6.10. In this example, the *zoo.tours.api* and *zoo.tours.reservations* models 
  make up the service since they consist of the interface and lookup functionality.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_6/images/Figure_6_10.png?raw=true)


> Note
> You aren't required to have four separate modules.
> We do so to illustrate the concepts.
> For example, the service provider interface and service locator could be in the same module.


## Declaring the Service Provider Interface

- First, the *zoo.tours.api* module defines a Java object called *Souvenir*. 
- It is considered part of the service because it will be referenced by the interface.

```java
package zoo.tours.api;

public class Souvenir {

    private String description;

    public String getDescription() { return description; }

    public void setDescription(String description) { 
        this.description = description;
    }

}
```

- Next, the module contains a Java *interface* type.
- This interface is called the *service provider interface* because it specifies what behaviour our service will have.
- In this case, it is a simple API with three methods.


```java
package zoo.tours.api;

public interface Tour {

    String name();
    int length();
    Souvenir getSouvenir();

}
```

- All three methods use the implicit *public* modifier, as shown in ***Chapter 1***, "Java Fundamentals".
- Since we are working with modules, we also need to create a *module-info.java* file so our module definition 
  exports the package containing the interface.

```java
module zoo.tours.api {
    exports zoo.tours.api;
}
```

- Now that we have both files, we can compile and package this module.

```shell
javac -d serviceProviderInterfaceModule \
  serviceProviderInterfaceModule/zoo/tours/api/*.java \
  serviceProviderInterfaceModule/module-info.java
```

```shell
jar -cvf mods/zoo.tours.api.jar -C serviceProviderInterfaceModule/ .
```

>Note 
> A service provider "interface" can be an *abstract* class rather than an actual *interface*.
> Since you will only see it as an *interface* on the exam, we use that term in the book.

- To review, the service includes the service provider interface and supporting classes it references.
- The service also includes the lookup functionality, which we will define next.


## Creating a Service Locator

- To complete our service, we need a service locator.
- A *service locator* is able to find any classes that implement a service provider interface.

- Luckily, Java provides a *ServiceLocator* class to help with this task.
- You pass the service provider interface type to its *load()*, and Java will return any implementation service it can 
  find.
- The following class shows it in action:


```java
package zoo.tours.reservations;

import java.util.*;
import zoo.tours.api.*;

public class TourFinder {

    public static Tour findSingleTour() {
        ServiceLoader<Tour> loader = ServiceLoader.load(Tour.class);
        for (Tour tour : loader) {
            return tour;
        }
        return null;
    }

    public static List<Tour> findAllTours() {
        List<Tour> tours = new ArrayList<>();
        ServiceLoader<Tour> loader = ServiceLoader.load(Tour.class);
        for (Tour tour : loader) {
            tours.add(tour);
        }
        return tours;
    }
}
```

- As you can see, we provided two lookup methods.
- The first is a convenience method if you are expecting exactly one *Tour* to be returned.
- The other returns a *List*, which accommodates any number of service providers.
- At runtime, there may be many service providers (or none) that are found by the service locator.

> Tip
> The ServiceLoader call is relatively expensive. If you are writing a real application, it is best to cache the result.


- Our module definition exports the package with the lookup class *TourFinder*.
- It requires the service provider interface package.
- It also has the *uses* directive since it will be looking up a service.

```java
module zoo.tours.reservations {
    exports zoo.tours.reservations;
    requires zoo.tours.api;
    uses zoo.tours.api.Tour;
}
```

- Remember that both *requires* and *uses* are needed, one for compilation and one for lookup.
- Finally, we compile and package the module.

```shell
javac -p mods -d serviceLocatorModule \
  serviceLocatorModule/zoo/tours/reservations/*.java  /
  serviceLocatorModule/module-info.java
```


```shell
jar -cvf mods/zoo.tours.reservations.jar -C serviceLocatorModule/ .
```

- Now that we have the *interface* and lookup logic, we have completed our service.

## Invoking from a Consumer

- Next up is to call the service locator by a consumer. A *consumer(or client)* refers to a module that obtains and 
  uses a service.
- Once the consumer has acquired a service via the service locator, it is able to invoke the methods provided by the 
  service provider interface.

```java
package zoo.visitor;

import java.util.*;
import zoo.tours.api.*;
import zoo.tours.reservations.*;

public class Tourist {

    public static void main(String[] args) {
        Tour tour = TourFinder.findSingleTour();
        System.out.println("Single tour: " + tour);

        List<Tour> tours = TourFinder.findAllTours();
        System.out.println("# tours: " + tours.size());
    }
}
```

- Our module definition doesn't need to know anything about the implementation since the 
  *zoo.tours.reservations* module is handling the lookup.

```java
module zoo.visitor {

    requires zoo.tours.api;
    requires zoo.tours.reservations;
}
```

- This time, we get to run a program after compiling and packaging.

```shell
javac -p mods consumerModule \
  consumerModule/zoo/visitor/*.java  \
  consumerModule/module-info.java
```

```shell
jar -cvf mods/zoo.visitor.jar -C consumerModule/ .
```

```shell
java -p mods -m zoo.visitor/zoo.visitor.Tourist
```

- The program outputs the following:

```
Single tour: null
# tours: 0
```

- Well, that makes sense. We haven't written a class that implements the interface yet.


## Adding a Service Provider

- A *service provider* is the implementation of a service provider interface.
- As we said earlier, at runtime it is possible to have multiple implementation classes or modules.
- We will stick to one here to simplicity.
- Our service provider is the *zoo.tours.agency* package because we've outsourced the running of tours to a third party.


```java
package zoo.tours.agency;

import zoo.tours.api.*;

public class TourImpl implements Tour {

    public String name() {
        return "Behind the Scenes";
    }
    public int length() {
        return 120;
    }
    public Souvenir getSouvenir() {
        Souvenir gift = new Souvenir();
        gift.setDescription("stuffed animal");
        return gift;
    }
}
```

- Again, we need a *module-info.java* file to create a module.

```java
module zoo.tours.agency {
    requires zoo.tours.api;
    provides zoo.tours.api.Tour with zoo.tours.agency.TourImpl;
}
```

- The module declaration requires the module containing the interface as a dependency.
- We don't export the package that implements the interface since we don't want callers referring to it directly.
- Instead, we use the *provides* directive.
- This allows us to specify that we provide an implementation of the interface with a specific implementation class.
- The syntax looks like this:

```
provides interfaceName with className;
```

>Note
> We have not exported the package containing the implementation.
> Instead, we have made the implementation available to a service provider using the interface.


- Finally, we compile and package it up.

```shell
javac -p mods -d serviceProviderModule \
  serviceProviderModule/zoo/tours/agency/*.java \
  serviceProviderModule/module-info.java
```

```shell
jar -cvf mods/zoo.tours.agency.jar -C serviceProvideModule/ .
```

- Now comes the cool part. We can run the Java program again.

```shell
java -p mods -m zoo.visitor/zoo.visitor.Tourist
```

- This time we see the following output:

```
Single tour: zoo.tours.agency.TourImpl@1936f0f5
# tours: 1
```















































