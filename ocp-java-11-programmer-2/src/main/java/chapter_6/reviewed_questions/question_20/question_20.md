20. Suppose we have a JAR file named cat-1.2.3-RC1.jar and Automatic-Module-Name in the MANIFEST.MF is set to dog.
    What should an unnamed module referencing this automatic module include in the *module-info.java*?

A. requires cat;
B. requires cat.RC;
C. requires cat-RC;
D. requires dog;
E. None of the above

---
### Explanation ###

- Correct Answer: Answer: E

- Trick question!
- An unnamed module doesn't use a module-info file.
- Therefore, option E is correct. 
- An unnamed module can access an automatic module.
- The unnamed module would simply treat the automatic module as a regular JAR without involving the *module.info* file.
---
