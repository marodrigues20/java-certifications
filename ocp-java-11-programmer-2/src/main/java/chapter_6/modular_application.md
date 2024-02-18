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
  [Appendix A](https://github.com/marodrigues20/java-certifications/tree/main/ocp-java-11-programmer-2/src/main/java/Appendix_A) 
  before continuing in this chapter.

