20. Which values when inserted into the black independently would allow the code to compile? (Choose all that apply.)

```
Console console = System.console();
String color = console.readLine("Favorite color? ");
console._______________("Your favorite color is %s", color);
```

A. reader().print <br>
B. reader().println <br>
C. format <br>
D. writer().print <br>
E. writer().println <br>
F. None of the above. <br>

Correct Answer: C

- *Console* includes a *format()* method that takes a *String* along with a list of arguments and writes it directly to 
  the output stream, making option C correct. <br>
- Options A and B are incorrect, as *reader()* returns a *Reader*, which does not define any print methods. <br>
- Options D and E would be correct if the line was just a *String*. Since neither of those methods take additional
  arguments, they are incorrect.