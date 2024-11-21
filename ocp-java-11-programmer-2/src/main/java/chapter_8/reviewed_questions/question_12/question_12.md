12. What are possible result of executing the following code? (Choose all that apply.)



```java
public static void main(String[] args){
    String line;
    var c = System.console();
    Writer w = c.writer();
    try(w){
        if((line = c.readLine("Enter your name: ")) != null)
            w.append(line);
        w.flush();
    }
}
```


A. The code runs but nothing is printed.            <br>
B. The code prints what was entered by the user.    <br>
C. An *ArrayIndexOutOfBoundsException* is throw.    <br>
D. A *NullPointerException* is thrown.              <br>
E. None of the above, as the code does not compile. <br>

Correct Answer: E

- The code does not compile, as the *Writer* methods *append()* and *flush()* both throw an *IOException* that must be
  handled or declared.
- Even without those lines of code, the *try-with-resources* statement itself must be handled or declared, since the
  *close()* method throws a checked *IOException* exception.
- For this reason, option E is correct. If the *main()* method was corrected to declare *IOException*, then the code
  would compile.
- If the *Console* was not available, it would throw a *NullPointerException* on the call to *c.writer()*; otherwise,
  it would print whatever the user typed in. For these reason, options B and D would be correct.