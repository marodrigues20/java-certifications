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

- A shallow copy does not create copies of the nested objects, making option C correct.
- Options B and D are incorrect because narrow and wide copies are not terms.
- Option A is incorrect because a deep copy does copy the nested objects.