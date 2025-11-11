6. Which statement are true about the *clone()* method? (Choose all that apply.)

A. Calling *clone()* on any object will compile. <br>
B. Calling *clone()*  will compile only if the class implements *Cloneable*.   <br>
C. if *clone()*  runs without exception, it will always create a deep copy.     <br>
D. If *clone()* runs without exception, it will always create a shallow copy.       <br>
E. If *clone*()* is not overridden and runs without exception, it will create a deep copy. <br>
F. If *clone()* is not overridden and runs without exception, it will create a shallow copy.  <br>


Answer: A; F.

- The *clone()* method is declared on the *Object* class.
- Option A is correct because it will always compile. However, the call will throw an exception if the class that is 
  being cloned does not implement the *Cloneable* interface.
- Assuming this interface is implemented, the default implementation creates a shallow copy, making option F correct.
- If the class wants to implement a deep copy, it must override the *clone()* method with a custom implementation.