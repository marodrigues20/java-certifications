18. What are some possible results of executing the following code? (Choose all that apply.)

```markdown
var x = Path.of("/animals/fluffy/..");
Files.walk(x.toRealPath().getParent()) // u1
    .map(p -> p.toAbsolutePath().toString()) // u2
    .filter(s -> s.endsWith(".java")) // u3
    .collect(Collectors.toList())
    .forEach(System.out::println);
```

A. It prints some files in the root directory.
B. It prints all files in the root directory.
C. *FileSystemLoopExcept* is thrown at runtime.
D. Another exception is thrown at runtime.
E. The code will not compile because of line u1.
F. The code will not compile because of line u2.