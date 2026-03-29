# OCP Java 11 Programmer I — Chapter 9: Advanced Class Design
## Abstract Classes, Interfaces & Polymorphism

---

## 1. Abstract Classes

Uma classe abstrata é uma classe **parcialmente construída** que outra classe precisa terminar.

```java
abstract class Bird {
    public abstract String getName(); // sem corpo — termina em ;
    public void printName() {
        System.out.print(getName());  // pode chamar o método abstrato
    }
}
```

**Regras:**
- Não pode ser instanciada diretamente.
- Pode ter variáveis de instância, construtores, métodos concretos e abstratos.
- O construtor roda via `super()` da subclasse.

```java
abstract class Bear {
    public Bear() { System.out.println("Bear constructor!"); }
}
class Panda extends Bear {
    // super() chamado automaticamente
    public static void main(String[] args) {
        new Panda(); // prints: Bear constructor!
    }
}
```

---

## 2. Modificadores incompatíveis com `abstract`

```java
public abstract final class Tortoise { }     // ❌ final impede herança
abstract class Whale {
    private abstract void sing();            // ❌ private impede override
}
abstract class Hippo {
    abstract static void swim();             // ❌ static não pode ser overridden
}
```

| Combinação | Motivo |
|---|---|
| `abstract final` | `abstract` exige herança, `final` impede |
| `abstract private` | `private` impede que a subclasse veja o método |
| `abstract static` | `static` pertence à classe, não pode ser overridden |

---

## 3. First Concrete Subclass

A **primeira classe concreta** na hierarquia deve implementar **todos** os métodos abstratos pendentes.

```
Animal (abstract)
│  └── abstract getType()
│
└── Bird (abstract) ──implements──► Fly (interface)
    │  └── abstract canSwoop()          └── void fly()
    │
    └── Swan (concrete) ──implements──► Swim (interface)
                                            └── void swim()
```

`Swan` precisa implementar: `getType()`, `canSwoop()`, `fly()`, `swim()` — **4 métodos**.

---

## 4. Reviewing Abstract Class Rules

| Regra |
|---|
| Não pode ser instanciada |
| Pode ter métodos abstratos e concretos |
| Métodos abstratos só existem em classes abstratas ou interfaces |
| `abstract` é incompatível com `final`, `private` e `static` |
| A first concrete subclass implementa todos os métodos abstratos pendentes |
| Modificadores de acesso válidos em métodos abstratos: `package-private`, `protected`, `public` |

---

## 5. Interfaces

Um contrato que define **o que** uma classe deve fazer.

```java
public interface Validator {
    boolean isValid(String value);           // public abstract (implícito)

    default void printResult(String value) { // default — tem implementação
        System.out.println(isValid(value));
    }

    static Validator create() {              // static — pertence à interface
        return value -> value != null && !value.isEmpty();
    }

    private boolean checkNotNull(String v) { // private — uso interno (Java 9+)
        return v != null;
    }

    private static String sanitize(String v) { // private static — uso interno estático
        return v.trim().toLowerCase();
    }
}
```

---

## 6. Implicit Modifiers

O compilador adiciona modificadores automaticamente:

| Elemento | Modificador implícito |
|---|---|
| Variáveis | `public static final` |
| Métodos abstratos | `public abstract` |
| Default methods | `public` |
| Static methods | `public` |

**Regra:** Em interfaces, tudo é `public`. Qualquer modificador menos restritivo → não compila.

```java
public interface Dance {
    private int count = 4;    // ❌ conflito com public static final implícito
    protected void step();    // ❌ conflito com public abstract implícito
}
```

---

## 7. Diferença entre Abstract Class e Interface

| | Abstract Class | Interface |
|---|---|---|
| Instanciável | ❌ | ❌ |
| Variáveis de instância | ✅ | ❌ (só `public static final`) |
| Construtores | ✅ | ❌ |
| Modificadores em métodos abstratos | `package-private`, `protected`, `public` | só `public` |
| Herança múltipla | ❌ (`extends` só uma) | ✅ (`implements` várias) |

---

## 8. Sintaxe válida de herança

```java
class1 extends class2                          // ✅
interface1 extends interface2, interface3      // ✅
class1 implements interface2, interface3       // ✅

class Cheetah extends CanRun {}               // ❌ class não extends interface
interface HasFur extends Hyena {}             // ❌ interface não extends class
```

---

## 9. Duplicate Interface Methods

```java
interface Herbivore { void eatPlants(); }
interface Omnivore  { void eatPlants(); void eatMeat(); }

class Bear implements Herbivore, Omnivore {
    public void eatPlants() { }  // ✅ uma implementação resolve os dois
    public void eatMeat()   { }
}
```

| Situação | Resultado |
|---|---|
| Mesma assinatura, mesmo return type | ✅ uma implementação resolve os dois |
| Assinaturas diferentes (parâmetros) | ✅ overload — implementa os dois |
| Mesma assinatura, return types incompatíveis | ❌ não compila |

> **Lembrete:** Assinatura = nome + parâmetros. Return type **não** faz parte da assinatura.

---

## 10. Default Method — Conflito

```java
interface Walk { default String getSpeed() { return "slow"; } }
interface Run  { default String getSpeed() { return "fast"; } }

class Camel implements Walk, Run {
    @Override
    public String getSpeed() { return Walk.super.getSpeed(); } // obrigatório resolver
}
```

Opções ao resolver o conflito:
1. Escrever implementação do zero.
2. Delegar para `Walk.super.getSpeed()`.
3. Delegar para `Run.super.getSpeed()`.

Não fazer nada → **não compila**. ❌

---

## 11. Polymorphism

```java
class Parent {
    String name = "Parent";
    String who() { return "Parent"; }
}
class Child extends Parent {
    String name = "Child";           // hiding — não override!
    @Override String who() { return "Child"; }
}

Parent p = new Child();
System.out.println(p.name);   // "Parent" ← variável, compile time
System.out.println(p.who());  // "Child"  ← método, runtime (override)
```

| | Resolvido em |
|---|---|
| Método de instância (override) | Runtime |
| Variável de instância / método estático | Compile time |

---

## 12. Casting

```java
interface Canine {}
class Dog  implements Canine {}
class Wolf implements Canine {}

Canine c = new Wolf();
Dog d = (Dog) c;          // compila ✅ — ClassCastException em runtime ❌
```

O compilador só barra se consegue **provar** que é impossível:

```java
Integer i = 5;
if (i instanceof List) { }  // ❌ não compila — Integer é final
```

| Tipo da referência | instanceof com interface | Resultado |
|---|---|---|
| Classe normal | qualquer interface | Compila ✅ |
| Classe `final` | interface não implementada | Não compila ❌ |

> **Lembrete:** Todos os Wrapper types são `final`: `Integer`, `Long`, `Double`, `Float`, `Boolean`, `Byte`, `Short`, `Character`.

---

## 13. Covariant Return Type

No override, o return type pode ser **igual ou mais específico** — nunca mais genérico.

```java
class Animal { Animal create() { return new Animal(); } }
class Dog extends Animal {
    @Override
    Dog create() { return new Dog(); }  // ✅ Dog é mais específico que Animal
}
```

---

## 14. Interface Rules — Checklist

**Interface Definition Rules:**
1. Interfaces não podem ser instanciadas.
2. Top-level interfaces não podem ser `protected` ou `private`.
3. São assumidas como `abstract` — não podem ser `final`.
4. Podem ter zero ou mais métodos abstratos.
5. Podem `extends` qualquer número de interfaces.
6. Referência de interface pode ser castada para qualquer tipo que a implemente — mas pode lançar `ClassCastException` em runtime.
7. Compilador barra `instanceof` com interface só se o tipo é `final` e não implementa a interface.
8. Método com corpo numa interface deve ser `default`, `private`, `static` ou `private static`.

**Abstract Interface Method Rules:**
1. Métodos abstratos só podem ser definidos em classes abstratas ou interfaces.
2. Métodos abstratos não podem ter corpo.
3. Override segue as regras de covariant return type e não pode restringir o modificador de acesso.
4. A first concrete subclass implementa todos os métodos abstratos herdados.

**Interface Implementation Rules:**
1. Uma classe pode implementar qualquer número de interfaces.
2. Deve implementar todos os métodos abstratos — ou ser `abstract`.
3. Pode fazer override de um `default` method ou simplesmente herdá-lo.
4. Se herda dois `default` methods com o mesmo nome — **deve** fazer override.

---

## 15. Inner Classes (Member Inner Class)

Classe definida dentro de outra classe, a nível de membro.

```java
public class Outer {
    private String name = "Outer";

    class Inner {
        private String name = "Inner";

        public void greet() {
            String name = "local";
            System.out.println(name);            // "local"
            System.out.println(this.name);       // "Inner"
            System.out.println(Outer.this.name); // "Outer"
        }
    }
}
```

**Instanciando Inner Class:**
```java
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();  // precisa de instância de Outer
inner.greet();
```

**Características:**
- Acessa membros `private` da classe externa.
- Precisa de uma instância da classe externa para existir.
- `Outer.this.campo` para aceder à instância da classe externa.
