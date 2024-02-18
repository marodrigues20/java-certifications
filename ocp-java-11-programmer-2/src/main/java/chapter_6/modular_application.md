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

