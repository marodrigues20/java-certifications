17. Assuming the */fox/food-schedule.csv* file exists with the specified contents, what is the expected
    output of calling *printData()* on it?

```markdown
    /fox/food-schedule.csv
    6am,Breakfast
    9am,SecondBreakfast
    12pm,Lunch
    6pm,Dinner
    
    void printData(Path path) throws IOException {
        Files.readAllLines(path) // r1
            .flatMap(p -> Stream.of(p.split(","))) // r2
            .map(q -> q.toUpperCase()) // r3
            .forEach(System.out::println);
    
    }
```

A. The code will not compile because of line r1.
B. The code will not compile because of line r2.
C. The code will not compile because of line r3.
D. It throws an exception at runtime.
E. It does not print anything at runtime.
F. None of the above.

