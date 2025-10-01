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

Answer: 