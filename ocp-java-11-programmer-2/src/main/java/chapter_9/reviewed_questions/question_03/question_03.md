3. What is the result of executing the following code? (Choose all that apply.)

```markdown
4: var p Paths.get("sloth.schedule");
5: var a = Files.readAttributes(p, BasicFileAttributes.class);
6: Files.mkdir(p.resolve(".backup"));
7: if(a.size()>0 && a.isDirectory()) {
8:      a.setTimes(null,null,null);
9: }
```


A. It compiles and runs without issue.
B. The code will not compile because of line 5.
C. The code will not compile because of line 6.
D. The code will not compile because of line 7.
E. The code will not compile because of line 8.
F. None of the above.