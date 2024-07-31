## 3. Which of the following statements about the *Callable call()* and *Runnable run()* methods are correct?
## (Choose all that apply.)

A. Both can throw unchecked exceptions. <br>
B. *Callable* takes a generic method argument. <br>
C. *Callable* can throw a checked exception. <br>
D. Both can be implemented with lambda expressions. <br>
E. *Runnable* returns a generic type. <br>
F. *Callable* returns a generic type. <br>
G. Both methods return *void* <br>

## Correct Answer: A; C; D; F

**Explanation:**

- All methods are capable of throwing unchecked exceptions, so option A is correct.
- Runnable and Callable statements both do not take any arguments, so option B is incorrect.
- Only Callable is compatible of throwing  checked exceptions, so option C is also correct.
- Both Runnable and Callable are functional interfaces that can be implemented with a lambda expression, so option D is 
- also correct.
- Finally, Runnable returns void and Callable  returns a generic type, making option F correct and making options E and G 
- incorrect.