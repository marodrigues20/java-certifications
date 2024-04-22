## 2. Given that the sum of the numbers from 1(inclusive) to 10(exclusive) is 45, what are the possible results of 
## executing  the following program? (Choose all that apply.)

```java
public class Bank {
    private Lock vault = new ReentrantLock();
    private int total = 0;
    public void deposit(int value){
        try{
            vault.tryLock();
            total += value;
        }finally {
            vault.unlock();
        }
    }

    public static void main(String[] args) {
        var bank = new Bank();
        IntStream.range(1,10).parallel()
                .forEach(s -> bank.deposit(s));
        System.out.println(bank.total);
    }
}
```

## Correct Answer: A