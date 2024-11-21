10. What are the requirements for a class that you want to serialize to a stream? (Choose all that apply.)

A. The class must be marked *final*.                            <br>
B. The class must extend the *Serializable* class.              <br>
C. The class must declare a *static serialVersionUID* variable. <br>
D. All *static* members of the class must be marked *transient*.<br>
E. The class must implement the *Serializable* interface.       <br>
F. All instance members of the class must be serializable of marked *transient*.   

Correct Answer: E; F

- For a class to be serializable, it must implement the *Serializable* interface and contain instance members that are 
  serializable or marked *transient*. 
- For these reason, option E and F are correct.
- Marking a class *final* does not impact its ability to be serilized, so option A is incorrect.
- Option B is incorrect, as *Serializable* is an interface, not a class. 
- Option C is incorrect.
- While it is a good practice for a serializable class to include a *static serialVersionUID* variable, it is not required.
- Finally, option D is incorrect as *static* members of the class are ignored on serialization already.