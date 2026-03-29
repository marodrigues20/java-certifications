# OCP Java 11 Programmer I — Capítulo 7: Methods and Encapsulation

---

## 1. Anatomy of a Method

Todo método Java segue esta estrutura:

```java
public static final int add(int a, int b) throws ArithmeticException {
    return a + b;
}
//  [1]    [2]   [3]  [4]  [5]         [6]
```

| # | Elemento                                                            | Obrigatório? |
|---|---------------------------------------------------------------------|:------------:|
| 1 | Access modifier (`public`, `private`, `protected`, package-private) | Não          |
| 2 | Optional specifier (`static`, `final`, `abstract`, `synchronized`)  | Não          |
| 3 | Return type (`int`, `void`, etc.)                                   | **Sim**      |
| 4 | Method name                                                         | **Sim**      |
| 5 | Parameter list (pode ser vazia `()`)                                | **Sim**      |
| 6 | Exception list (`throws ...`)                                       | Não          |

---

## 2. Access Modifiers

| Modificador                     | Mesma classe | Mesmo pacote | Subclasse (outro pacote) | Qualquer lugar |
|---------------------------------|:------------:|:------------:|:------------------------:|:--------------:|
| `private`                       | ✅           | ❌           | ❌                       | ❌             |
| package-private *(sem keyword)* | ✅           | ✅           | ❌                       | ❌             |
| `protected`                     | ✅           | ✅           | ✅                       | ❌             |
| `public`                        | ✅           | ✅           | ✅                       | ✅             |

---

## 3. Optional Specifiers

- **`static`** — pertence à classe, não à instância
- **`final`** — não pode ser sobrescrito (override)
- **`abstract`** — sem corpo, obriga subclasse a implementar
- **`synchronized`** — controle de concorrência (Cap 7 do Programmer II)

Pode haver **mais de um** specifier:

```java
public static final void print() { }  // válido
```

---

## 4. Varargs

Permite número variável de argumentos — **deve ser o último parâmetro**:

```java
public void log(String prefix, int... numbers) {
    for (int n : numbers) System.out.println(prefix + n);
}

log("n=", 1, 2, 3);              // válido
log("n=");                        // válido — numbers é um array vazio
log("n=", new int[]{1, 2});      // também válido
```

> ⚠️ **Regra do exame:** só **um** varargs por método, e sempre no final.

---

## 5. Pass by Value

Java **sempre** passa por valor — inclusive referências de objetos:

```java
void change(int x) { x = 99; }  // não afeta o original

void change(StringBuilder sb) {
    sb.append(" world");           // afeta o objeto (mesma referência)
    sb = new StringBuilder("new"); // NÃO afeta a variável original
}
```

---

## 6. Encapsulation

Esconder o estado interno e expor só através de métodos controlados:

```java
public class BankAccount {
    private double balance;  // estado privado

    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;  // validação centralizada
    }
}
```

> ✅ **Regra:** campo `private` + getter/setter `public` = classe encapsulada.

---

## 7. Overloading

Mesmo nome, **assinatura diferente** (tipo ou quantidade de parâmetros):

```java
public int sum(int a, int b)          { return a + b; }
public double sum(double a, double b) { return a + b; }       // overload válido
public int sum(int a, int b, int c)   { return a + b + c; }   // overload válido
// public double sum(int a, int b)    { ... }                  // ERRO — só muda return type
```

Ordem de preferência do compilador ao resolver overload:

1. Match exato
2. Promoção de tipo (`int` → `long` → `double`...)
3. Autoboxing (`int` → `Integer`)
4. Varargs

---

## 8. Constructors

Construtor tem o **mesmo nome da classe** e **sem return type**:

```java
public class Dog {
    private String name;
    private int age;

    public Dog() {                     // construtor padrão (no-arg)
        this.name = "Unknown";
        this.age = 0;
    }

    public Dog(String name, int age) { // construtor com parâmetros
        this.name = name;
        this.age = age;
    }
}
```

**Regras importantes:**
- Se você **não declarar nenhum** construtor, o compilador gera `public ClassName() {}` automaticamente
- Se você declarar **qualquer** construtor, o compilador **não** gera mais o padrão
- Construtor **não é herdado**

---

## 9. `this` e `this()`

**`this`** — referência à instância atual:

```java
public Dog(String name) {
    this.name = name;  // distingue o campo do parâmetro
}
```

**`this()`** — chama **outro construtor da mesma classe** (constructor chaining):

```java
public class Dog {
    private String name;
    private int age;

    public Dog() {
        this("Unknown", 0);  // delega para o construtor abaixo
    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

**Regras do exame:**
- `this()` deve ser a **primeira linha** do construtor
- Não pode criar ciclo: A chama B, B chama A → erro de compilação

```java
public Dog() {
    System.out.println("antes");  // ERRO — this() não é a primeira linha
    this("Unknown", 0);
}
```

---

## 10. Initializer Blocks

Executam **antes** do construtor, na ordem em que aparecem:

```java
public class Dog {
    private String name;

    { System.out.println("1. instance initializer"); }  // roda sempre

    public Dog() {
        System.out.println("2. constructor");
    }
}
// Output ao criar new Dog():
// 1. instance initializer
// 2. constructor
```

**`static` initializer** — roda uma vez quando a classe é carregada:

```java
static { System.out.println("classe carregada"); }
```

**Ordem de execução completa:**

1. Static initializers *(uma vez por classe)*
2. Instance initializers *(a cada `new`)*
3. Construtor

---

## 11. The `static` Keyword

### Static Methods e Fields

Pertencem à **classe**, não à instância. Podem ser chamados sem criar objeto:

```java
public class Counter {
    private static int count = 0;  // compartilhado por todas as instâncias
    private String name;

    public Counter(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }
}

Counter a = new Counter("A");
Counter b = new Counter("B");
System.out.println(Counter.getCount());  // 2
```

---

### Static vs. Instance

| | Static | Instance |
|---|---|---|
| Pertence a | Classe | Objeto |
| Acessa campos static | ✅ | ✅ |
| Acessa campos instance | ❌ | ✅ |
| Usa `this` | ❌ | ✅ |

```java
public class MyClass {
    private int x = 10;
    private static int y = 20;

    public static void staticMethod() {
        System.out.println(y);  // ✅
        System.out.println(x);  // ❌ ERRO — não pode acessar campo de instância
    }
}
```

---

### Static Variables

Inicializadas uma vez, compartilhadas por todas as instâncias:

```java
public class Config {
    public static final String APP_NAME = "OOPMate";  // constante — convenção: UPPER_CASE
    public static int instanceCount = 0;
}

System.out.println(Config.APP_NAME);  // OOPMate
```

> ✅ **Boa prática:** constantes estáticas sempre `static final` + `UPPER_CASE`

> ⚠️ **Regra do exame:** chamar método estático via referência nula **não lança NullPointerException**:
> ```java
> Counter c = null;
> c.getCount();  // ✅ compila e funciona — o compilador usa a classe, não a instância
> ```

---

### Static Initialization

Roda **uma vez** quando a classe é carregada pela JVM — antes de qualquer instância ser criada:

```java
public class DBConnection {
    private static final String URL;

    static {
        String env = System.getenv("ENV");
        URL = env != null ? "prod-db" : "dev-db";
        System.out.println("Classe carregada! URL = " + URL);
    }

    public static String getURL() {
        return URL;
    }
}
```

---

## 12. Static Imports

Permite usar membros estáticos **sem o nome da classe**:

```java
import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.System.out;

public class Circle {
    public static void main(String[] args) {
        double radius = 5.0;

        double area = PI * pow(radius, 2);
        out.println("Area: " + area);  // 78.53...

        double diagonal = sqrt(pow(3, 2) + pow(4, 2));
        out.println("Diagonal: " + diagonal);  // 5.0
    }
}
```

> ✅ **Uso clássico no dia a dia com JUnit:**
> ```java
> import static org.junit.jupiter.api.Assertions.assertEquals;
> import static org.junit.jupiter.api.Assertions.assertTrue;
>
> assertEquals(5, result);
> assertTrue(result > 0);
> ```

### Regras do Exame — Static Imports

**1. A sintaxe correta é `import static`, não `static import`:**
```java
import static java.lang.Math.sqrt;  // ✅
static import java.lang.Math.sqrt;  // ❌ ERRO de compilação
```

**2. Conflito entre dois static imports com mesmo nome:**
```java
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Long.MAX_VALUE;  // ❌ ERRO — ambiguidade!
```

**3. Static import não substitui import normal:**
```java
import static java.util.Collections.sort;  // importa só o método
import java.util.List;                      // ainda necessário!
import java.util.ArrayList;

List<String> names = new ArrayList<>();
sort(names);  // ✅
```

**4. Membro local tem precedência sobre static import:**
```java
import static java.lang.Math.sqrt;

public class MyClass {
    public static double sqrt(double x) { return 0; }  // método local

    public static void main(String[] args) {
        sqrt(16);  // chama o método LOCAL, não o Math.sqrt
    }
}
```

---

## 13. Overloading Avançado

### Primitive Types — Tabela de Tamanhos

| Tipo | Bits | Intervalo |
|------|:----:|-----------|
| `byte` | 8 | -128 a 127 |
| `short` | 16 | -32.768 a 32.767 |
| `int` | 32 | ~-2 bilhões a ~2 bilhões |
| `long` | 64 | ~-9 quintilhões a ~9 quintilhões |
| `float` | 32 | ~7 dígitos decimais de precisão |
| `double` | 64 | ~15 dígitos decimais de precisão |

> ⚠️ `float` e `int` têm ambos 32 bits — mas `float` representa decimais, por isso fica **depois** de `long` na promoção.

> ❌ **Nunca use `float` ou `double` para dinheiro** — use `BigDecimal`:
> ```java
> double price = 0.1 + 0.2;
> System.out.println(price);  // 0.30000000000000004 😱
>
> BigDecimal price = new BigDecimal("0.1").add(new BigDecimal("0.2"));
> System.out.println(price);  // 0.3 ✅
> ```

---

### Primitive Promoting vs. Autoboxing

- **Primitive promoting** — `int → long → double...` — tudo ainda primitivo, só muda o tamanho
- **Autoboxing** — `int → Integer` — converte primitivo para seu **wrapper object**

> ⚠️ **Regra:** promoção de primitivo **sempre vence** autoboxing no overloading.

```java
public void test(Integer x) { System.out.println("Integer — autoboxing"); }
public void test(long x)    { System.out.println("long — promoção"); }

test(5);  // long — promoção vence autoboxing!
```

Ordem de promoção: `byte → short → int → long → float → double`

---

### Autoboxing + Widening

O compilador **não faz as duas coisas ao mesmo tempo**:

```java
public void test(Long x) { System.out.println("Long"); }

int i = 5;
// test(i);  // ❌ ERRO — int não faz autobox direto para Long (dois passos!)

test(Long.valueOf(i));  // ✅ — você controla os passos explicitamente
                        // int → long (promoção automática) → Long (valueOf)
```

> ⚠️ **Regra do exame:** o compilador aceita **um** passo de conversão automática, nunca dois encadeados.

---

### Overloading com Varargs

Varargs é sempre a **última opção** do compilador:

```java
public void test(int x)    { System.out.println("int"); }
public void test(int... x) { System.out.println("varargs"); }

test(5);     // int — match exato vence varargs
test(5, 6);  // varargs — única opção
test();      // varargs — array vazio
```

---

### Overloading com Reference Types

O compilador escolhe o **tipo mais específico**:

```java
public void print(String s) { System.out.println("String"); }
public void print(Object o) { System.out.println("Object"); }

print("hello");       // String — mais específico
print(new Object());  // Object — match exato
print(null);          // String — mais específico vence
```

---

### Overloading com Generics — Type Erasure

Generics sofrem **type erasure** em runtime — não é possível fazer overload só mudando o tipo genérico:

```java
// ❌ ERRO — ambos viram List após erasure
public void process(List<String> list)  { }
public void process(List<Integer> list) { }

// ✅ Válido — parâmetros extras diferentes
public void process(List<String> list, String s)  { }
public void process(List<Integer> list, int i)    { }
```

---

### Ordem Completa de Preferência do Compilador

| Prioridade | Estratégia |
|:---:|---|
| 1 | Match exato |
| 2 | Promoção de primitivo (`int → long → double...`) |
| 3 | Autoboxing (`int → Integer`) |
| 4 | Widening de referência (`Integer → Number → Object`) |
| 5 | Varargs |
