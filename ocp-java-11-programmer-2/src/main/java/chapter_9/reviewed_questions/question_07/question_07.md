7. If the current working directory is */zoo* and the path */zoo/turkey* does not exists, then what is the result of 
   executing  the following code? (Choose all that apply.)

```
Path path = Paths.get("turkey");
if(Files.isSameFile(path, Paths.get("/zoo/turkey"))) // z1
    Files.createDirectories(path.resolve("info"));   // z2
```

A. The code compiles and runs without issue, but it does not create any directories.
B. The directory */zoo/turkey* is created.
C. The directory */zoo/turkey/into* is created.
D. The code will not compile because of line z1.
E. The code will not compile because of line z2.
F. It compiles but throws an exception at runtime.