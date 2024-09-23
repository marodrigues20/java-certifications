## Which of the following are valid *Callable* expression? (Choose all that apply.)

A. ```a -> {return 10;} ```       <br>
B. ```() { String s = "";}```     <br>
C. ```() -> 5 ```                 <br>
D. ```() -> { return null }```    <br>
E. ```() -> "The" + "Zoo"```      <br>
F. ```(int count) -> count + 1``` <br>
G. ```() -> {System.out.println("Giraffe"); return 10;}```    <br>

Correct Answers: C, E, G.

- A *Callable* lambda expression takes no values and returns a generic type; 
- Therefore, options C, E, and G are correct.
- Options A and F are incorrect because they both take an input parameter.
- Option B is incorrect because it does not return a value.
- Option D is not a valid lambda expression, because it is missing a semicolon at the end of the *return* statement
  which is required when inside braces {}.

