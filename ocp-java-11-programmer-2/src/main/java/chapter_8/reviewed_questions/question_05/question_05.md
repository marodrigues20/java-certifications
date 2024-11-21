5. Which of the following are true? (Choose all that apply.)

A. *System.console()* will throw an *IOException* if a *Console* is not available.       <br>
B. *System.console()* will return *null* if a *Console* is not available.                <br>
C. A new *Console* object is created every time *System.console()* is called.            <br>
D. *Console* can be used only for writing output, not reading input.                     <br>
E. *Console* includes a *format()* method to write data to the console's output stream.  <br>
F. *Console* includes a *println()* method to write data to the console's output stream. <br>   


Correct Answer: B; E

- The JVM creates one instance of the *Console* object as a singleton, making the option C incorrect.
- If the console is unavailable, *System.console()* will return *null*, making option B correct.
- The method cannot throw an *IOException* because it is not declared as a checked exception. Therefore, option A is 
  incorrect.
- Option D is incorrect, as a *Console* can be used for both reading and writing data.
- The *Console* class includes a *format()* method to write data to the output stream, making option E correct.
- Since there is no *println()* method, as *writer()* must be called first, option F is incorrect.

