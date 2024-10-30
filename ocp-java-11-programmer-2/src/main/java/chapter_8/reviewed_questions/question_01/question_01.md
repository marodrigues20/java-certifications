1. Which class would be best to use to read a binary file into a Java object?
A. ObjectWriter <br>
B. ObjectOutputStream <br>
C. BufferedStream <br>
D. ObjectReader <br>
E. FileReader <br>
F. ObjectInputStream <br>
G. None of the above <br>

Correct Answer: F

- Since the question asks about putting data into a structured object, the best class would be one that deserializes the 
  data.
- Therefore, *ObjectInputStream* is the best choice.
- *ObjectWriter*,*BufferedStream*, and *ObjectReader*, are not I/O stream classes.
- *ObjectOutputStream* is an I/O class but is used to serialize data, not deserialize it.
- *FileReader* can be used to read text file data and construct an object, but the question asks what would be the best
  class for binary data.