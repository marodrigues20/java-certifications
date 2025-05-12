4. If the current working directory is */user/home*, then what is the output of the following code?

```
var p = Paths.get("/zoo/animals/bear/koala/food.txt");
System.out.print(p.subpath(1,3).getName(1).toAbsolutePath());
```

A. bear
B. animals
C. /user/home/bear
D. /user/home/animals
E. /user/home/food.txt
F. The code does not compile
G. The code compiles but throws an exception at runtime.


