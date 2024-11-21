8. Which of the following are true statements about serialization in Java? (Choose all that apply.)

A. Deserialization involves converting data into Java objects.  <br>
B. Serialization involves converting data into Java objects.    <br>
C. All nonthread classes should be marked Serializable.         <br>
D. The *Serializable* interface requires implementation *serialize()* and *deserialize()* methods.  <br>
E. *Serializable* is a functional interface.    <br>
F. The *readObject()* method of *ObjectInputStream* may throw a *ClassNotFoundException* even if the return object is
   cast to a specific type. <br>

Correct Answer: A; F

- In Java, serialization is the process of turning an object to a stream, while deserialization is the process of turning
  that stream back into an object. For this reason, option A is correct, and option B is incorrect.
- Option C is incorrect, because many nonthread classes are not marked *Serializable* for various any abstract method,
  making options D and E incorrect.
- Finally, option F is correct, because *readObject()* declares the *ClassNotFoundException* even if the class is not 
  cast to a specific type.