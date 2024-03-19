8. Which of the following compiles and is equivalent to this loop?

```
List<Unicorn> all = new ArrayList<>();
for(Unicorn current : ServiceLoader.load(Unicorn.class))
    all.add(current);
```


A. 
```
for(Unicorn current : ServiceLoader.load(Unicorn.class))
    .getStream()
    .collect(Collectors.toList());
```


B. 
```
for(Unicorn current : ServiceLoader.load(Unicorn.class))
    .stream()
    .collect(Collectors.toList());
```

C. 
```
List<Unicorn> all = ServiceLoader.load(Unicorn.class)
    .getStream()
    .map(Provider::get)
    .collect(Collectors.toList());
```


D. 

```
List<Unicorn> all = ServiceLoader.load(Unicorn.class)
    .stream()
    .map(Provider::get)
    .collect(Collectors.toList());
```


E. None of the above

---
### Explanation ###
 
- Correct Answer: D

- There is no getStream() method on a *ServiceLoader*, making options A and C incorrect.
- Option B does  not compile because the stream() method returns a list of *Provider* interfaces and needs to be converted
  to the *Unicorn* interfaces we are interested in. Therefore, option D is correct.
---



