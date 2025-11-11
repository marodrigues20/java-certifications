15. What modifiers must be used with the *serialPersistentFields* field in a class? (Choose all that apply.)

A. final        <br>
B. private      <br>
C. protected    <br>
D. public       <br>
E. transient    <br>
F. static       <br>


Answer: A, B, F.

- The *serialPersistentFields* field is used to specify which fields should be used in serialization.
- It must be declared *private static final*, or it will be ignored. 
- Therefore, option A, B, and F are correct.