9. Assuming */* is the root dictory within the file system, which of the following are true statements? <br>
   (Choose all that apply.)

A. */home/parrot* is an absolute path.  <br>
B. */home/parrot* is a directory.       <br>
C. */home/parrot* is a relative path.   <br>
D. *new File("/home")* will throw an exception if */home* does not exist.       <br>
E. *new File("/home").delete()* throws an exception if */home* does not exist.  <br>

Correct Answer: A;

- Paths that begin with the root directory are absolute paths, so option A is correct, and option C is incorrect.
- Option B is incorrect because the path could be a file or directory within the file system.
- There is no rule that files have to end witha file extension.
- Option D is incorrect, as it is possible to create a *File* reference to files and directories that do not exist.
- Option E is also incorrect. The *delete()* method returns *false* if the file or directory cannot be deleted.