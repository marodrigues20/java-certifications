19. Fill in the blank with the proper method to deserialize an object.

```markdown
public Object ______________ throws ObjectStreamException {
    // return an object
}
```

A. readObject()
B. readReplace()
C. readResolve()
D. writeObject()
E. writeReplace()
F. writeResolve()

Answer: C


- Option B and F are incorrect because these methods names are not used by any serialization or deserialization process.
- Option A and D are incorrect because the return type for these methods is *void*, not *Object*. 
- Option E is almost correct, as that is a valid method signature, but our question asks for the method used in deserialization,
  not serialization.
- Option C is the correct answer.