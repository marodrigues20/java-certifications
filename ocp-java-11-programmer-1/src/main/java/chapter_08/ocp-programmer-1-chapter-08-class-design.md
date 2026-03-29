# OCP Java 11 Programmer I — Chapter 8: Class Design

## 1. Understanding Inheritance

- Inheritance allows a subclass to automatically include `public` and `protected` members of the parent class.
- Java supports **single inheritance** only — a class can only extend one parent.
- Every class that does not explicitly extend another class implicitly extends `Object`.
- `private` members of the parent exist in the child but are **not directly accessible**.
- Package-private members are accessible only if the child is in the **same package**.

```java
public class Animal {
    public String name;
    protected int age;
    private int speed; // not accessible in child
}

public class Dog extends Animal {
    public void display() {
        System.out.println(name); // OK — public
        System.out.println(age);  // OK — protected
        // System.out.println(speed); // ERROR — private
    }
}
```

### Access Modifier Summary

| Modifier    | Same Class | Same Package | Subclass | Everywhere |
|-------------|------------|--------------|----------|------------|
| `private`   | ✅         | ❌           | ❌       | ❌         |
| *default*   | ✅         | ✅           | ❌       | ❌         |
| `protected` | ✅         | ✅           | ✅       | ❌         |
| `public`    | ✅         | ✅           | ✅       | ✅         |

---

## 2. Inheriting `Object`

- Every class in Java implicitly extends `Object` if no other `extends` is declared.

```java
public class Dog { }
// is the same as:
public class Dog extends Object { }
```

### Key methods inherited from `Object`

| Method       | What it does                              |
|--------------|-------------------------------------------|
| `toString()` | Returns a String representation of the object |
| `equals(Object obj)` | Compares two objects for equality |
| `hashCode()` | Returns a numeric hash code of the object |

### `toString()`

By default prints something like `Dog@1b6d3586`. Override to make it useful:

```java
public class Dog {
    private String name;

    public Dog(String name) { this.name = name; }

    @Override
    public String toString() {
        return "Dog{name=" + name + "}";
    }
}

Dog d = new Dog("Rex");
System.out.println(d); // Dog{name=Rex}
```

### `equals()`

By default compares **references** (same as `==`). Override to compare **content**:

```java
public class Dog {
    private String name;

    public Dog(String name) { this.name = name; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dog)) return false;
        Dog other = (Dog) obj;
        return this.name.equals(other.name);
    }
}

Dog d1 = new Dog("Rex");
Dog d2 = new Dog("Rex");

System.out.println(d1 == d2);      // false — different references
System.out.println(d1.equals(d2)); // true — same content
```

> ⚠️ If you override `equals()`, best practice is to also override `hashCode()`.

---

## 3. Constructors and `super()`

- When a child object is created, the **parent constructor is always called first**.
- If `super()` is not explicitly called, Java automatically inserts `super()` on the first line — calling the parent's **no-argument constructor**.
- If the parent has **no no-argument constructor**, the child **must** explicitly call `super(args)`.
- `super()` must always be the **first line** of the constructor.

```java
public class Animal {
    public Animal(String name) {
        System.out.println("Animal: " + name);
    }
}

public class Dog extends Animal {
    public Dog() {
        super("Rex"); // must be first line
        System.out.println("Dog created");
    }
}

// Output when calling new Dog():
// Animal: Rex
// Dog created
```

---

## 4. Calling Overloaded Constructors with `this()`

- `this()` allows a constructor to call **another constructor in the same class**, avoiding code duplication.
- `this()` must be the **first line** of the constructor.
- `this()` and `super()` **cannot** be used together in the same constructor.
- Cyclic constructor calls are not allowed.

```java
public class Dog {
    private String name;
    private String breed;

    public Dog(String name) {
        this(name, "Unknown"); // calls the constructor below
    }

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }
}
```

### Execution order with `this()`

The most deeply called constructor always executes first:

```java
public class Car {
    public Car() {
        this("Toyota");       // step 1 — calls Car(String)
    }
    public Car(String brand) {
        this(brand, 2024);    // step 2 — calls Car(String, int)
    }
    public Car(String brand, int year) {
        this.brand = brand;   // step 3 — executes first
        this.year = year;
    }
}

// Execution order:
// 1. Car(String brand, int year)
// 2. Car(String brand)
// 3. Car()
```

---

## 5. Overriding vs Hiding

### Method Overriding — instance methods

- The child **rewrites** the parent method behaviour.
- At runtime, the method executed depends on the **type of the object** (runtime).
- This is the foundation of **polymorphism**.

```java
public class Animal {
    public void makeSound() {
        System.out.println("Generic sound");
    }
}

public class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
}

Animal a = new Dog();
a.makeSound(); // Woof! — Dog's version runs (runtime type)
```

### Method Hiding — static methods

- Static methods are **not overridden** — they are **hidden**.
- The method executed depends on the **type of the variable** (compile time).

```java
public class Animal {
    public static void identify() {
        System.out.println("I am an Animal");
    }
}

public class Dog extends Animal {
    public static void identify() {
        System.out.println("I am a Dog");
    }
}

Animal a = new Dog();
a.identify(); // I am an Animal — variable type decides (compile time)
```

### Summary

| Type            | Depends on...                        | Name       |
|-----------------|--------------------------------------|------------|
| Instance method | Type of **object** (runtime)         | Overriding |
| Static method   | Type of **variable** (compile time)  | Hiding     |
| Variable        | Type of **variable** (compile time)  | Hiding     |

---

## 6. Hiding Variables

- When a child declares a variable with the **same name** as the parent, it hides the parent's variable.
- Unlike method overriding, variables are **never polymorphic** — always follow the **variable type at compile time**.

```java
public class Animal {
    String name = "Animal";
}

public class Dog extends Animal {
    String name = "Dog"; // hides Animal's name
}

Animal a = new Dog();
System.out.println(a.name); // Animal — variable type decides

Dog d = new Dog();
System.out.println(d.name); // Dog
```

---

## 7. Calling the Parent Version of an Overridden Method with `super.method()`

- Use `super.methodName()` to call the parent's version of an overridden method.
- Unlike `super()` in constructors, `super.method()` can be called on **any line** of the method.

```java
public class Animal {
    public void eat() {
        System.out.println("Animal eating");
    }
}

public class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("Dog eating");
        super.eat(); // calls Animal's version
    }
}

Animal a = new Dog();
a.eat();
// Dog eating
// Animal eating
```

Use `super.method()` when the child wants to **extend** the parent's behaviour, not replace it entirely:

```java
public class Vehicle {
    public void start() {
        System.out.println("Engine started");
    }
}

public class Car extends Vehicle {
    @Override
    public void start() {
        super.start();                       // reuses parent behaviour
        System.out.println("AC turned on");  // adds extra behaviour
    }
}
```

---

## 8. `final` Keyword

| Applied to | Effect                       |
|------------|------------------------------|
| Variable   | Cannot be reassigned         |
| Method     | Cannot be overridden         |
| Class      | Cannot be inherited/extended |

```java
// final variable
final int speed = 100;
speed = 200; // ERROR

// final method
public final void breathe() { }
// cannot be overridden in a subclass

// final class
public final class MyClass { }
// cannot be extended
```

---

## 9. Polymorphism and Casting

- Polymorphism allows a parent-type variable to reference a child object.

### Upcasting — implicit, always safe

```java
Animal animal = new Dog(); // no explicit cast needed
```

### Downcasting — explicit, can fail at runtime

```java
Animal animal = new Dog();
Dog dog = (Dog) animal; // explicit cast required
```

### ClassCastException

```java
Animal animal = new Cat();
Dog dog = (Dog) animal; // compiles OK, throws ClassCastException at runtime!
```

### `instanceof` — check before downcasting

```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal; // safe
}
```

### Summary

| Type        | Explicit cast needed? | Can fail?               |
|-------------|-----------------------|-------------------------|
| Upcasting   | ❌ No                 | ❌ Never                |
| Downcasting | ✅ Yes                | ✅ `ClassCastException` |

---

> ⚠️ Note: Abstract Classes belong to **Chapter 9**, not Chapter 8.
