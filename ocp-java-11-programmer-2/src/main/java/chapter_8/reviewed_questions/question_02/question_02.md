2. Which of the following are methods available on instances of the *java.io.File* class? (Choose all that apply.)

A. mv()
B. createDirectory()
C. mkdirs()
D. move()
E. renameTo()
F. copy()
G. mkdir()

Answer: C; E; G

- The command to move a file or directory using a *File* is *renameTo()*, not *mv()* or *move()*, making option A and D
  incorrect, and option E correct.
- The commands to create a directory using a *File* are *mkdir()* and *mkdirs()*, not *createDirectory()*, making option B 
  incorrect, and options C and G correct.
- The *mkdirs()* differs from *mkdir()* by creating any missing directories along the path.
- Finally, option F is incorrect as there is no command to copy a file in the File class.
- You would need to use an I/O stream to copy the file along with its contents.

