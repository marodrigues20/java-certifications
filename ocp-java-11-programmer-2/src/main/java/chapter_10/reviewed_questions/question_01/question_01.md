1. Which interfaces or classes are in a database-specific JAR file? (Choose all that apply)

A. Driver <br>
B. Driver's implementation <br>
C. DriverManager <br>
D. DriverManager's implementation <br>
E. PreparedStatement  <br>
F. PreparedStatement's implementation <br>


Answer: B, F


- The *Driver* and *PreparedStatement* interfaces are part of the JDK, making options A and E incorrect.
- The concrete *DriverManager* class is also part of the JDK, making options C and D incorrect.
- Options B and F are correct since the implementation of these interfaces is part of the database-specif driver JAR file.