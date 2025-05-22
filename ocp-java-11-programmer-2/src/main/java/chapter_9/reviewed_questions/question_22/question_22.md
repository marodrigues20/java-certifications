22. Assuming the path referenced by *m* exits as a file, which statements about the following method are correct?
    (Choose all that apply.)

```markdown
void duplicateFile(Path m, Path x) throws Exception {
    var r = Files.newBufferedReader(m);
    var w = Files.newBufferedWriter(x, StandardOpenOption.APPEND);
    String currentLine = null;
    while ((currentLine = r.readLine()) != null)
        w.write(currentLine);
}
```

A. If the path referenced by x does not exist, then it correctly copies the file.
B. If the path referenced by x does not exist, then a new file will be created.
C. If the path referenced x does not exist, then an exception will be thrown at runtime.
D. If the path referenced x exists, then an exception will be thrown at runtime.
E. The method contains a resource leak.
F. The method does not compile.