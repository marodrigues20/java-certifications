9. What is the output of the following code?

```markdown
var path1 = Path.of("/pets/..cat.txt");
var path2 = Paths.get("./dog.txt");
System.out.println(path1.resolve(path2));
System.out.println(path2.resolve(path1));
```

A. /cats.txt <br>
   /dog.txt


B. /cats.txt/dog.txt <br>
   /cat.txt

C. /pets/../cat.txt/./dog.txt <br>
   /pets/../cat.txt


D. /pets/../cat.txt/./dog.txt  <br>
   ./dog.txt/pets/../cat.txt

E. None of the above

