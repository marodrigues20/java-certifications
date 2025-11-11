10. Which types of resources do you need to close to help avoid a denial of service? (Choose all that apply.)

A. Annotations
B. Exceptions
C. I/O
D. JDBC
E. String

Answer: C, D.

- Any resources accessing things outside your program should be closed.
- Options C and D are correct because I/O and JDBC meet this criteria.