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



























































