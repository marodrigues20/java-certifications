13. Which of the following are true statements about a class *Camel* with a single instance variable
    *List<String> species*?

A. If *Camel* is well encapsulated, then it must have restricted extensibility. <br>
B. If *Camel* is well encapsulated, then it must be immutable. <br>
C. If *Camel* has restricted extensibility, then it must have good encapsulation. <br>
D. If *Camel* has restricted extensibility, then it must be immutable. <br>
E. If *Camel* is immutable, then it must have good encapsulation. <br>
F. If *Camel* is immutable, then it must restrict extensibility. <br>

Answer: E, F.

- Option A is incorrect because a good encapsulation requires *private* state rather than declaring the class *final*.
- Option B is incorrect because the well-encapsulated *Camel* class can have a getter that exposes the *species* variable to be modified.
- Options C and D are incorrect because a class can be *final* while having *public* variables and be mutable.
- Option E is correct because methods that expose *species* could change it, which would prevent immutability.
- Option F is correct because you cannot enforce immutability.