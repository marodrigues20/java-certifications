20. Assuming the current directory is /seals/harp/food, what is result of executing the folloing code?

```markdown
final Path path = Paths.get(".").normalize();
int count = 0;
for(int i-0; i < path.getNameCount(); ++i){
    count++;
}
System.out.println(count);
```

A. 0
B. 1
C. 2
D. 3
E. 4
F. The code compiles but throws an exception at runtime.