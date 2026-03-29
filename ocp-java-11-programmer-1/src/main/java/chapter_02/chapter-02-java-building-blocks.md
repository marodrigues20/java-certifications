# OCP Java 11 Programmer I — Chapter 2: Java Building Blocks

---

## 1. Construtores

Para criar um objeto em Java:

```java
Park p = new Park();
```

O `Park()` é o **construtor**. Duas regras simples:
- Tem o **mesmo nome** da classe
- **Não tem tipo de retorno**

```java
public class Chick {
    public Chick() { }        // construtor ✅
    public void Chick() { }   // método normal, NÃO é construtor ❌
}
```

Se não declarar nenhum, Java cria um construtor padrão vazio automaticamente.

---

## 2. Ordem de Inicialização

Quando você cria um objeto, Java segue uma ordem fixa:

1. **Variáveis e static initializer blocks** — na ordem que aparecem (quando a classe é carregada)
2. **Instance fields e instance initializer blocks** — **interleaved**, na ordem que aparecem
3. **Construtor** — sempre por último

```java
public class Exemplo {
    static int x = 1;                         // 1. static field
    static { System.out.println("static"); }  // 2. static block

    int y = 2;                                // 3. instance field
    { System.out.println("instance block"); } // 4. instance block
    int z = 3;                                // 5. instance field

    public Exemplo() { }                      // 6. construtor
}
```

⚠️ Pegadinha clássica:

```java
public class Egg {
    public Egg() { number = 5; }  // construtor → seta 5
    private int number = 3;       // field → seta 3
    { number = 4; }               // initializer → seta 4
    // Ordem real: 3 → 4 → 5
    // Resultado: number = 5
}
```

---

## 3. Os 8 Tipos Primitivos

| Tipo      | Tamanho          | Sufixo Obrigatório |
|-----------|------------------|--------------------|
| `boolean` | true/false       | —                  |
| `byte`    | 8 bits           | —                  |
| `short`   | 16 bits          | —                  |
| `int`     | 32 bits          | —                  |
| `long`    | 64 bits          | `L`                |
| `float`   | 32 bits decimal  | `f`                |
| `double`  | 64 bits decimal  | —                  |
| `char`    | 16 bits Unicode  | —                  |

```java
long big    = 3123456789L;  // sem o L → NÃO COMPILA
float price = 9.99f;        // sem o f → Java trata como double → NÃO COMPILA
```

⚠️ `String` **NÃO é primitivo** — é objeto.

### Literais e bases numéricas

```java
long max    = 3123456789L;  // long
float price = 9.99f;        // float
int octal   = 017;          // octal   (prefixo 0)
int hex     = 0xFF;         // hex     (prefixo 0x)
int binary  = 0b10;         // binário (prefixo 0b)
```

### Underscore em literais (Java 7+)

```java
int million = 1_000_000;   // ✅
double ok   = 1_000.99;    // ✅
double bad1 = _1000.0;     // ❌ início
double bad2 = 1000.0_;     // ❌ fim
double bad3 = 1000_.0;     // ❌ ao lado do ponto decimal
```

---

## 4. Primitivo vs. Referência

| | Primitivo | Referência |
|---|---|---|
| Guarda | o valor direto | o endereço do objeto na heap |
| Aceita `null` | ❌ | ✅ |
| Tem métodos | ❌ | ✅ |

```java
int value = null;            // ❌ NÃO COMPILA
String s   = null;           // ✅

int len = value.length();    // ❌ int não tem métodos
String ref = "hello";
int len = ref.length();      // ✅
```

### Stack vs. Heap

```
STACK                          HEAP
─────────────────              ──────────────────────────
│ main()                │      │                        │
│  ┌─────────────────┐  │      │  ┌──────────────────┐  │
│  │ int age = 25    │  │      │  │  Object: Person  │  │
│  │ (valor direto)  │  │      │  │  ──────────────  │  │
│  └─────────────────┘  │      │  │  name = "Alex"   │  │
│                        │      │  └──────────────────┘  │
│  ┌─────────────────┐  │      │                        │
│  │ Person p  ──────────────▶ │                        │
│  │ (referência)    │  │      │                        │
│  └─────────────────┘  │      │                        │
─────────────────              ──────────────────────────
```

| | Stack | Heap |
|---|---|---|
| O que guarda | Pilha de métodos, primitivos locais e referências | Objetos |
| Tem nome | ✅ | ❌ |
| Gerenciado por | JVM automaticamente | Garbage Collector |

> **Stack** → pilha de chamadas de métodos
> **Heap** → onde os objetos vivem de facto na memória

---

## 5. Identificadores — Regras de Nomenclatura

- **Pode** começar com letra, `$` ou `_`
- **Pode** conter números, mas não pode começar com número
- **Não pode** ser `_` sozinho (desde Java 9)
- **Não pode** ser palavra reservada (`class`, `int`, `for`...)

```java
long okIdentifier;      // ✅
float $OK2;             // ✅
boolean _alsoOK;        // ✅

int 3DMap;              // ❌ começa com número
byte hollywood@vine;    // ❌ @ não é permitido
double public;          // ❌ palavra reservada
short _;                // ❌ underscore sozinho
```

---

## 6. Declarando Múltiplas Variáveis

```java
String s1, s2;                  // ✅ duas declaradas
String s3 = "yes", s4 = "no";  // ✅ duas declaradas e inicializadas
int i1, i2, i3 = 0;            // ✅ três declaradas, só i3 inicializada

int num, String value;          // ❌ tipos diferentes na mesma linha
```

⚠️ `i1` e `i2` como **fields de instância** → valor padrão `0` (compila).
⚠️ `i1` e `i2` como **variáveis locais** → obrigatório inicializar antes de usar (não compila).

---

## 7. Valores Padrão

Só se aplicam a **fields de instância e de classe**. Variáveis locais **não têm valor padrão**.

| Tipo | Valor padrão |
|------|-------------|
| `boolean` | `false` |
| `byte, short, int, long` | `0` |
| `float, double` | `0.0` |
| `char` | `'\u0000'` |
| Objetos (referências) | `null` |

---

## 8. `var` — Local Variable Type Inference (Java 10+)

```java
var name = "Hello";  // compilador infere String
var size = 7;        // compilador infere int
```

### 7 Regras do `var`

1. Só funciona em **variáveis locais**
2. **Deve ser inicializado na mesma linha**
3. O **tipo não pode mudar**, o valor pode
4. Não pode ser inicializado com **`null`**
5. Não funciona em **parâmetros de método**
6. Não funciona em **múltipla declaração**
7. Não pode ser nome de **classe ou interface**

```java
var x = 7;
x = "texto";           // ❌ tipo inferido como int

var n = null;          // ❌ compilador não sabe o tipo

var a = 2, b = 3;      // ❌ múltipla declaração

public int add(var a)  // ❌ parâmetro de método

public class var { }   // ❌ nome de classe

var var = "var";       // ✅ var como nome de variável (não faça isso!)
```

---

## 9. Escopo de Variáveis

Regra simples: **variável vive do ponto onde é declarada até o `}` do seu bloco.**

```java
public void eatIfHungry(boolean hungry) {
    if (hungry) {
        int bitesOfCheese = 1;
    }  // bitesOfCheese morre aqui
    System.out.println(bitesOfCheese);  // ❌ NÃO COMPILA
}
```

Bloco interno **enxerga** o externo, mas não vice-versa:

```java
if (hungry) {
    int cheese = 1;
    {
        System.out.println(cheese);  // ✅ interno enxerga externo
        var tiny = true;
    }
    System.out.println(tiny);        // ❌ externo não enxerga interno
}
```

### Escopo por tipo de variável

| Tipo | Morre quando... |
|---|---|
| **Local** | Fecha o bloco `}` |
| **Instância** | Objeto é coletado pelo GC |
| **Classe (`static`)** | Programa termina |

---

## 10. Garbage Collection

O GC remove objetos da heap automaticamente quando **nenhuma referência aponta para eles**.

Um objeto fica elegível para GC em duas situações:
1. A referência é setada para `null`
2. A referência sai de escopo

```java
public void metodo() {
    String one = new String("a");  // objeto "a" na heap
    String two = new String("b");  // objeto "b" na heap

    one = two;   // "one" aponta para "b" → objeto "a" elegível para GC ♻️
    one = null;  // "one" não aponta mais para nada
}
// fim do método → "two" sai de escopo → objeto "b" elegível para GC ♻️
```

⚠️ Pontos importantes:
- `System.gc()` existe mas **não garante** que o GC vai rodar
- `finalize()` está **depreciado** desde Java 9

### Novidades no GC — Java 21

| Feature | Descrição |
|---|---|
| **Generational ZGC (JEP 439)** | ZGC com suporte a gerações — foca em objetos jovens, menos pausa e mais eficiência |
| **Virtual Threads (JEP 444)** | Stacks menores → menos pressão na heap |

---

*OCP Oracle Certified Professional Java SE 11 Programmer I Study Guide — Boyarsky & Selikoff (Sybex, 2020)*
