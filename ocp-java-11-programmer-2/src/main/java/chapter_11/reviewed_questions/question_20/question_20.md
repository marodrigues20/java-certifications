20. The following code prints true. What is true about the *Wombats* class implementation of the *clone()* method?

```markdown
Wombats original = new Wombats();
original.names = new ArrayList<>();
Wombats clone = (Wombats) original.clone();
System.out.println(original.getNames() == cloned.getNames());
```


A. It creates a deep copy. <br>
B. It creates a narrow copy. <br>
C. It creates a shallow copy. <br>
D. It creates a wide copy. <br>


Answer: C