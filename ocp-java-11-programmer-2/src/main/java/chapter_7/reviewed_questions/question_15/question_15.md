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


A. It outputs 3 4. <br>
B. It outputs 4 3. <br>
C. The code will not compile because of line 6. <br>
D. The code will not compile because of line 7. <br>
E. The code will not compile because of line 8. <br>
F. It compiles but throws an exception at runtime. <br>


Correct Answer: A

- The code compiles and runs without issue, so options C, D, E, and F are incorrect.
- The *collect()* operation groups the animals into those that do and do not start with the letter p and three animals
  that do. The logical complement operator (!) before the *startWith()* method means that result are reversed, so the
  output is 3 4 and option A is correct, making option B incorrect.