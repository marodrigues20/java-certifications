21. Assume the *source* instance passed to the following method represents a file that exists.
Also, assume */flip/sounds.txt" exists as a file prior to executing this method.
When this method is executed, which statement correctly copies the file to the path specified by */flip/sounds.txt*?

```markdown
void copyIntoFlipDirectory(Path source) throws IOException {
    var dolphinDir = Path.of("/flip");
    dolphinDir = Files.createDirectories(dolphinDir);
    var n = Paths.get("sounds.txt");
}
```

A. Files.copy(source, dolphinDir)
B. Files.copy(source, dolphinDir.resolve(n), StandardCopyOption.REPLACE_EXISTING)
C. Files.copy(source, dolphinDir, StandardCopyOption.REPLACE_EXISTING)
D. Files.copy(source, dolphinDir.resolve(n))
E. The method does not compile, regardless of what is placed in the blank.
F. The method compiles but throws an exception at runtime, regardless of what is placed in the blank.