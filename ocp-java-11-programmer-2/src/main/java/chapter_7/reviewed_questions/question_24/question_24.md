## What statement about the following class definition are true? (Choose all that apply)

```
public class TicketManager {
    private int tickets;
    private static TicketManager instance;
    private TicketManager() { }
    static synchronized TicketManager getInstance() {         // k1
        if (instance == null) instance = new TicketManager(); // k2
        return instance;
    }
    
    public int getTicketCount() { return tickets; }
    public void addTickets(int value) {tickets += value;} // k3
    public void sellTickets(int value) {
        synchronized (this) {   //k4
            tickets -= value;
        }
    }
}
```


A. It compiles without issue.   <br>
B. The code will not compile because of line k2. <br>
C. The code will not compile because of line k3.    <br>
D. The locks acquired on k1 and k4 are on the same object.  <br>
E. The class correctly protects the tickets data from race conditions. <br>
F. At most one instance of TicketManager will be created in an application that uses this class. <br>

Correct Answer: A, F

- The class compiles without issue, so option A is correct, so option A is correct, and options B and C are incorrect.
- Since *getInstance()* is a *static* method and *sellTickets()* is an instance method, lines k1 and k4 synchronize on 
  different objects, making option D incorrect.
- The class is not thead-safe because the *addTickets()* method is not synchronized, and option E is incorrect.
- For example, one thread could call *sellTickets()* while another thread calls *addTickets()*. 
- These methods are not synchronized with each other and could cause an invalid number of tickets due to a race condition.
- Finally, option F is correct the *getInstance()* method is *synchronized*. Since the constructor is *private*, this method
  is the only way to create an instance of *TicketManager* outside the class.
- The thread to enter the method will set the *instance* variable, and all other threads will use the existing value.
- This is actually a singleton pattern.
