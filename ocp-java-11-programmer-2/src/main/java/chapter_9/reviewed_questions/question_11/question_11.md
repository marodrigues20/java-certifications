11. Assume *money.txt* is a file that exists in the current working directory.
    Which statement about the following code snippet are correct? (Choose all that apply.)

```markdown
Files.move(Path.of("monkey.txt"), Paths.get("/animals"),
StandardCopyOption.ATOMIC_MOVE,
LinkOption.NOFOLLOW_LINKS);
```

```markdown
A. If */animals/monkey.txt* exists, then it will be overwritten at runtime.
B. If */animals* exists as an empty directory, then */animals/monkey.txt* will be the new location of the file.
C. If *monkey.txt* is a symbolic link, then the file it points to will be moved at runtime.
D. If the move is successful and another process is monitoring the file system, then it will not see an incomplete file
   at runtime.
E. The code will always throw an exception at runtime.
F. None of the above.
```
