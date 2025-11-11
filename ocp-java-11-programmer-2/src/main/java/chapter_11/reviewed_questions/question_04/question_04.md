4. Which of the following can fill in the blank to make a defensive copy of *weights*?

```markdown
    public class Turkey {
        private ArrayList<Double> weights;
        public Turkey(ArrayList<Double> weights) {
            this.weights = ______________;
        }
    }
```


A. weights <br>
B. new ArrayList<>(weights) <br>
C. weights.clone() <br>
D. (ArrayList) weights.clone()
E. weights.copy()
F. (ArrayList) weights.copy()

Answer: B, D.

- Option A is incorrect because it does not make a copy.
- Options E and F are incorrect because *ArrayList* does not have a *copy()* method.
- Option C is incorrect because the *clone()* method returns an *Object* and needs to be cast, so that option does not
  compile.
- Option B and D are correct because they copy the *ArrayList* using the copy constructor and *clone()* method, respectively.