18. Suppose that you need to write data that consists of *int*, *double*, *boolean*, and *String* values to a file that 
    maintains the data types of the original data. You also want the data to be performant on large files. 
    Which three java.io stream classes can be chained to best achieve this result? (Choose three.)

A. FileWriter
B. FileOutputStream
C. BufferedOutputStream
D. ObjectOutputStream
E. DirectoryOutputStream
F. PrintWriter
G. PrintStream


Correct Answer: B; C; D

- Since you need to write primitives and String values, the OutputStream classes are appropriate.
- Therefore, you can eliminate options A and F since they use Writer classes.
- Next, *DirectoryOutputStream* is not a *java.io* class, so option E is incorrect.
- The data should be written to the file directly using the *FileOutputStream* class, buffered with the 
  *BufferedOutputStream* class, and automatically serialized with the *ObjectOutputStream* class, so option B, C, and D
  are correct.
- *PrintStream* is an *OutputStream*, so it could be used to format the data. 
- Unfortunately, since everything is converted to a *String*, the underlying data type information would be lost.
- For this reason, option G is incorrect.