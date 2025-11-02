12. What is this code an example of?

```markdown
public void validate(String amount) {
    for (var ch : amount.toCharArray())
        if (ch < '0' || ch> '9')
            throw new IllegalArgumentException("invalid");
}
```

A. Blacklist <br>
B. Graylist  <br>
C. Orangelist <br>
D. Whitelist <br>
E. None of the above <br>


Answer: