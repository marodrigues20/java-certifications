19. Assuming the directories and files referenced exist and are not symbolic links, 
what is the result of executing the following code?

```markdown
var p1 = Path.of("/lizard",".")
    .resolve(Path.of("walking.txt"));
var p2 = new File("/lizard/././actions/../walking.txt")
    .toPath();
System.out.print(Files.isSameFile(p1,p2));
System.out.print(" ");
System.out.print(p1.equals(p2));
System.out.print(" ");
System.out.print(p1.normalize().equals(p2.normalize()));
```

A. true true true
B. false false false
C. false true false
D. true false true
E. true false false
F. The code does not compile.