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


Answer: D


- The validation code checks that each character is between  0 and 9.
- Since it is comparing to allowed values; this is an example of a whitelist, and option D is correct.
- If it were the opposite, it would be a blacklist. There is no such thing as a gray or orange list.