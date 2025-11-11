17. Which techniques can prevent an attacker from creating a top-level subclass that 
    overrides a method called from the constructor.

A. Adding *final* to the class
B. Adding *final* to the method
C. Adding *transient* to the class
D. Adding *transient* to the method
E. Making the constructor *private*
F. None of the above

Answer: A, B, E

- Options A and E are correct because they prevent subclasses from being created outside the class definition.
- Option B is also correct because it prevents overriding the method.
- Option C and D are incorrect because *transient* is a modifier for variables, not classes or methods.