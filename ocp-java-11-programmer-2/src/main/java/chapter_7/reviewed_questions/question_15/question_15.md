## Which statement about the following code snipped is correct?

```
2: var cats = Stream.of("leopard", "lynx", "ocelot", "puma")
3:         .parallel();
4: var bears = Stream.of("panda", "grizzly", "polar").parallel();
5: var data = Stream.of(cats, bears).flatMap(s -> s)
6:        .collect(Collectors.groupingByCurrent(
7:                s -> !s.startWith("p")));
8: System.out.println(data.get(false).size()
9:    + " " + data.get(true).size());
```


A. It outputs 3 4.
B. It outputs 4 3.
C. The code will not compile because of line 6.
D. The code will not compile because of line 7.
E. The code will not compile because of line 8.
F. It compiles but throws an exception at runtime.


Correct Answer: B