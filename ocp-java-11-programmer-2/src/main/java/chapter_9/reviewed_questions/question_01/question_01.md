1. What is the output of the following  code?

```markdown
4: var path = Path.of("/user/./root","../kodiacbear.txt");
5: path.normalize().relativize("/lion");
6: System.out.println(path);
```

A. ../../lion
B. /user/kodiacbear.txt
C. ../user/kodiacbear.txt
D. /user/./root/../kodiacbear.txt
E. The code does not compile.
F. None of the above

Answer: 