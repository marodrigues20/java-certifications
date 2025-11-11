2. Which of the following is the code an example of? (Choose all that apply.)

```markdown
public final class Worm {
    private int length;

    public Worm(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}   
```


A. Immutability
B. Input validation
C. Limiting accessibility
D. Restricting extensibility
E. None of the above


Answer: A, C, D

- Since the class is *final*, it restricts extensibility, making option D correct.
- The *private* variable limits accessibility, making option C correct.
- Finally, option A is correct. This is an immutable class since it's not possible to change the state after construction.
- This class does not do any validation, making option B incorrect.