13. For the copy() method show here, assume that the source exists as a regular file and that the target does not.
    What is the result of the following code?

```markdown
var p1 = Path.of(".","/","goat.txt").normalize(); // k1
var p2 = Path.of("mule.png");
Files.copy(p1, p2, StandardCopyOption.COPY_ATTRIBUTES); // k2
System.out.println(Files.isSameFile(p1, p2));
```

A. It will output false
B. It will output true.
C. It does not compile because of line k1.
D. It does not compile because of line k2.
E. None of the above.
