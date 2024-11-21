6. Which statement about closing I/O streams are correct? (Choose all that apply.)

A. *InputStream* and *Reader* instances are the only I/O stream that should be closed after use.    <br>
B. *OutputStream* and *Writer* instances are the only I/O stream that should be closed after use.   <br>
C. *InputStream/OutputStream* and *Reader/Writer* all should be closed after use.                   <br>
D. A tradition *try* statement can be used to close an I/O stream.                                  <br>
E. A *try-with-resources* can be used to close an I/O stream.                                       <br>
F. None of the above.


Correct Answer: C; D; E



- All I/O streams should be closed after use or a resource leak might ensue, making option C correct.
- While a **try-with-resource** statement is the preferred way to close an I/O stream, it can be closed with a traditional
  **try** statement that uses a **finally** block. For this reason, both options D and E are correct.

