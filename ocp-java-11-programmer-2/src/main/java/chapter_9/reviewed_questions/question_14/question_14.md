14. Assume */monkeys* exists as a directory containing multiple files, symbolic links,
    and subdirectories. Which statements about the following code is correct?


```markdown
var f = Path.of("/monkeys");
        try (var m =
        Files.find(f, 0, (p, a) -> a.isSymbolicLink())) { // y1
        m.map(s -> s.toString())
        .collect(Collectors.toList())
        .stream()
        .filter(s -> s.toString.endsWith(".txt")) // y2
        .forEach(System.out::println);
        }
```

A. It will print all symbolic links in the directory tree ending in .txt
B. It will print the target of all symbolic links in the directory ending in .txt.
C. It will print nothing
D. It does not compile because of line y1.
E. It does not compile because of line y2.
F. It compiles but throws an exception at runtime.