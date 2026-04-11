# Chapter 5 — Core Java APIs
**OCP Oracle Certified Professional Java SE 11 Programmer I**
**Boyarsky & Selikoff (Sybex, 2020)**

---

## Índice
1. [String — Concatenação e Imutabilidade](#1-string--concatenação-e-imutabilidade)
2. [String — Métodos Importantes](#2-string--métodos-importantes)
3. [StringBuilder](#3-stringbuilder)
4. [String Pool & Equality](#4-string-pool--equality)
5. [Arrays](#5-arrays)
6. [ArrayList](#6-arraylist)
7. [Math APIs](#7-math-apis)
8. [Pontos-Bomba para o Exame](#8-pontos-bomba-para-o-exame)

---

## 1. String — Concatenação e Imutabilidade

### As 3 Regras de Concatenação

| # | Regra |
|---|-------|
| 1 | Se ambos os operandos são numéricos → adição |
| 2 | Se um deles é `String` → concatenação |
| 3 | Avalia da esquerda para a direita |

```java
System.out.println(1 + 2);           // 3    (regra 1)
System.out.println("a" + "b");       // ab   (regra 2)
System.out.println("a" + "b" + 3);   // ab3  (regras 2+3)
System.out.println(1 + 2 + "c");     // 3c   (regras 1+2+3)
System.out.println("c" + 1 + 2);     // c12  (regras 2+3)

// ARMADILHA CLÁSSICA — verifica sempre os tipos!
int three = 3;
String four = "4";
System.out.println(1 + 2 + three + four);
// 1+2=3 → 3+three=6 → 6+four="64"  ← resultado: "64"
```

### Imutabilidade
Uma `String` **nunca muda** após ser criada. Não pode ser maior, menor, nem ter
caracteres alterados. Todos os métodos devolvem sempre um **novo objecto**.

```java
// ARMADILHA: resultado ignorado — s3 continua "12"
String s2 = "1";
String s3 = s2.concat("2");
s3.concat("3");             // novo objecto criado mas ignorado!
System.out.println(s3);     // 12
System.out.println(s2);     // 1  (imutável — nunca mudou)
```

> **Porquê imutável?** `String` é `final` — não pode ter subclasses que adicionem
> comportamento mutável. É uma decisão de design para segurança e performance
> (permite o String Pool).

---

## 2. String — Métodos Importantes

> Para todos estes métodos: a String é uma sequência de caracteres e
> **Java conta índices a partir de 0**.

### Tabela de Métodos

| Método | Assinatura | O que faz |
|---|---|---|
| `length()` | `int length()` | Nº de caracteres (conta de 1) |
| `charAt()` | `char charAt(int index)` | Char no índice (conta de 0) |
| `indexOf()` | `int indexOf(int ch / String str, [int from])` | 1º índice do match; `-1` se não encontrar |
| `substring()` | `String substring(int begin, [int end])` | Inclui begin, **exclui** end |
| `toLowerCase/toUpperCase()` | `String toLowerCase()` | Muda capitalização |
| `equals/equalsIgnoreCase()` | `boolean equals(Object o)` | Compara conteúdo |
| `startsWith/endsWith()` | `boolean startsWith(String s)` | Verifica prefixo/sufixo |
| `replace()` | `String replace(char old, char new)` | Substitui caracteres/substrings |
| `contains()` | `boolean contains(CharSequence s)` | Verifica se contém |
| `strip/trim()` | `String strip()` | Remove whitespace. `strip()` = Java 11 + Unicode |
| `stripLeading/Trailing()` | `String stripLeading()` | Remove só início / só fim — Java 11 |

### length() vs charAt()
```
"animals" → índices:
 a  n  i  m  a  l  s
 0  1  2  3  4  5  6

length() = 7  (conta de 1)
charAt(0) = 'a'  (conta de 0)
charAt(7) → StringIndexOutOfBoundsException!
```

### indexOf()
- Devolve `-1` quando não encontra — nunca lança exception
- Pode começar a procurar a partir de um índice com `fromIndex`

```java
String s = "animals";
s.indexOf('a');        // 0  (1ª ocorrência)
s.indexOf("al");       // 4
s.indexOf('a', 4);     // 4  (começa a procurar no índice 4)
s.indexOf("al", 5);    // -1 (não encontrou após índice 5)
```

### substring() — o mais tricky do exame
```
Visualização dos índices para substring:
 a  n  i  m  a  l  s
↑  ↑  ↑  ↑  ↑  ↑  ↑  ↑
0  1  2  3  4  5  6  7
                        ↑ posição "end of string"
```

```java
String s = "animals";
s.substring(3);        // "mals"  — do 3 até ao fim
s.substring(3, 4);     // "m"     — índice 3, exclui 4
s.substring(3, 7);     // "mals"  — equivalente ao primeiro
s.substring(3, 3);     // ""      — string vazia! NÃO é exception
// s.substring(3, 2)   → EXCEPTION — begin > end
// s.substring(3, 8)   → EXCEPTION — end > length
```

> **Regra:** `substring(begin, end)` inclui `begin`, exclui `end`.
> `substring(x, x)` → string vazia `""`.
> `substring(x, y)` com x > y → exception.

### strip() vs trim()
```java
String text = " abc\t ";
text.trim().length();          // 3 → "abc"
text.strip().length();         // 3 → "abc"
text.stripLeading().length();  // 5 → "abc\t "  (remove só o início)
text.stripTrailing().length(); // 4 → " abc"    (remove só o fim)
```
> `strip()` é Java 11 e suporta Unicode (ex: `\u2000`). `trim()` não suporta Unicode.
> Para o exame OCP 11, prefere `strip()`.

### Method Chaining
Cada chamada devolve um **novo objecto** String. Lê da esquerda para a direita.

```java
String result = "AniMaL   ".trim().toLowerCase().replace('a', 'A');
// "AniMaL   " → trim() → "AniMaL" → toLowerCase() → "animal" → replace() → "AnimAl"
System.out.println(result); // AnimAl

// ARMADILHA: a String original NUNCA muda
String a = "abc";
String b = a.toUpperCase();             // novo objecto "ABC"
b = b.replace("B", "2").replace('C', '3'); // "A2C" → "A23"
System.out.println("a=" + a); // a=abc  (imutável!)
System.out.println("b=" + b); // b=A23
```

---

## 3. StringBuilder

### Por que StringBuilder existe?
```java
// INEFICIENTE — cria 27 objectos String!
String alpha = "";
for(char current = 'a'; current <= 'z'; current++)
    alpha += current;
// Cada += cria um novo objecto String no heap
// O anterior fica elegível para GC → muito trabalho desnecessário!

// EFICIENTE — reutiliza o mesmo objecto
StringBuilder alpha = new StringBuilder();
for(char current = 'a'; current <= 'z'; current++)
    alpha.append(current);
// Um único objecto — mutável — muito mais eficiente!
```

### String vs StringBuilder

| | `String` | `StringBuilder` |
|---|---|---|
| Mutável? | ❌ Não | ✅ Sim |
| Chaining devolve | Novo objecto | A mesma instância |
| Quando usar | Valores fixos | Construção dinâmica |
| `equals()` implementado? | ✅ Sim (conteúdo) | ❌ Não (referência) |

### Três formas de construir
```java
StringBuilder sb1 = new StringBuilder();        // vazio
StringBuilder sb2 = new StringBuilder("animal");// com valor inicial
StringBuilder sb3 = new StringBuilder(10);      // capacidade inicial (≠ size!)
// sb3.length() == 0  ← capacidade não é o mesmo que tamanho!
```

### Mutabilidade e Chaining
> Quando encadeias métodos no StringBuilder, ele modifica o **próprio estado**
> e devolve **referência para si mesmo**. Não cria novos objectos!

```java
// ARMADILHA CLÁSSICA: quantos objectos StringBuilder existem?
StringBuilder a = new StringBuilder("abc"); // UM único objecto
StringBuilder b = a.append("de");  // b aponta para o MESMO objecto que a!
b = b.append("f").append("g");     // ainda o mesmo objecto

System.out.println("a=" + a); // a=abcdefg
System.out.println("b=" + b); // b=abcdefg  ← mesmo objecto!
System.out.println(a == b);   // true        ← mesma referência!
```

### Métodos Importantes

| Método | O que faz |
|---|---|
| `append(x)` | Acrescenta ao fim — aceita quase qualquer tipo |
| `insert(offset, x)` | Insere na posição indicada — desloca os seguintes |
| `delete(start, end)` | Remove de `start` até **antes** de `end` |
| `deleteCharAt(index)` | Remove exactamente um char |
| `replace(start, end, str)` | Apaga `[start,end[` e insere `str` |
| `reverse()` | Inverte todos os caracteres |
| `toString()` | Converte para `String` |
| `charAt / indexOf / length / substring` | Igual à `String` |

```java
// insert() — atenção aos índices que mudam!
StringBuilder sb = new StringBuilder("animals");
sb.insert(7, "-");   // "animals-"   (inseriu no fim)
sb.insert(0, "-");   // "-animals-"  (inseriu no início)
sb.insert(4, "-");   // "-ani-mals-" (inseriu no meio)

// delete() — end fora do range NÃO dá exception, trunca ao fim
StringBuilder sb2 = new StringBuilder("abcdef");
sb2.delete(1, 3);    // "adef"  (remove índices 1 e 2)
sb2.delete(1, 100);  // "a"     (end > length → trunca ao fim) ✅

// deleteCharAt() — index fora do range DÁ exception
// new StringBuilder("adef").deleteCharAt(5) → EXCEPTION 💥

// replace() — apaga [start,end[ e insere nova string
StringBuilder sb3 = new StringBuilder("pigeon dirty");
sb3.replace(3, 6, "sty");   // "pigsty dirty"
sb3.replace(3, 100, "");    // "pig" (end > length → permitido)

// substring() devolve String — NÃO modifica o StringBuilder!
StringBuilder sb4 = new StringBuilder("animals");
String sub = sb4.substring(0, 4); // "anim" — sb4 continua "animals"!
```

---

## 4. String Pool & Equality

### JVM Memory — O Mapa Completo
```
JVM MEMORY
├── HEAP
│   ├── YOUNG GENERATION
│   │   ├── Eden Space       ← new String("x") nasce AQUI
│   │   └── Survivor (S0/S1) ← objectos que sobrevivem Minor GC
│   ├── OLD GENERATION (Tenured)
│   │   └── objectos que sobreviveram muitos GCs
│   └── STRING POOL (área especial dentro do Heap desde Java 7)
│       └── literais e constantes compile-time
├── METASPACE (Java 8+)
│   └── classes, métodos, metadata
└── STACK
    └── variáveis locais e referências
```

> **Nota histórica:** Antes do Java 7, o String Pool ficava em **PermGen**
> (fora do Heap, tamanho fixo, podia causar `OutOfMemoryError`).
> Desde Java 7 está dentro do Heap e é gerido normalmente pelo GC.

### Quando vai para o Pool?
A regra de ouro: se o compilador consegue saber o valor **antes de o programa correr** → pool.
Se o valor só é conhecido **quando o programa está a correr** → heap normal (Eden Space).

| Expressão | Pool? | Porquê |
|---|---|---|
| `"Hello World"` | ✅ | Literal puro — compile-time |
| `"Hello" + " World"` | ✅ | Duas constantes — compilador resolve |
| `"Hello" + variavel` | ❌ | `variavel` só conhecida em runtime |
| `new String("Hello")` | ❌ | Força novo objecto no Eden Space |
| `str.trim()` / qualquer método | ❌ | Método corre em runtime |
| `new String("Hello").intern()` | ✅ | `intern()` força entrada no pool |

### Como a JVM gere o Pool — passo a passo
```
String x = "Hello World";
→ JVM pergunta: "Hello World" já está no pool?
→ NÃO → cria no pool → x recebe endereço @ADDR_1

String y = "Hello World";
→ JVM pergunta: "Hello World" já está no pool?
→ SIM → reutiliza → y recebe o MESMO endereço @ADDR_1
→ x == y → TRUE  (mesmo endereço)

String z = new String("Hello World");
→ new String() diz: "ignora o pool, cria novo objecto"
→ cria no Eden Space → z recebe @ADDR_2
→ x == z → FALSE  (@ADDR_1 ≠ @ADDR_2)
→ x.equals(z) → TRUE  (conteúdo igual)
```

### Comparação de Strings
```java
String x = "Hello World";
String y = "Hello World";
System.out.println(x == y);           // true  (pool — mesmo endereço)

String z = " Hello World".trim();     // runtime → Eden Space
System.out.println(x == z);           // false
System.out.println(x.equals(z));      // true  (conteúdo igual) ✅

String w = new String("Hello World"); // Eden Space
System.out.println(x == w);           // false
System.out.println(x.equals(w));      // true ✅

String name2 = new String("Hello World").intern(); // força o pool
System.out.println(x == name2);       // true ✅

// compile-time vs runtime
String first  = "rat" + 1;                          // pool → "rat1"
String second = "r" + "a" + "t" + "1";              // pool → "rat1"
String third  = "r" + "a" + "t" + new String("1");  // Eden → não é pool

System.out.println(first == second);          // true  (ambos no pool)
System.out.println(first == third);           // false (third no heap)
System.out.println(first == third.intern());  // true  (intern encontrou no pool)
```

### equals() em StringBuilder — armadilha!
```java
// StringBuilder NÃO implementa equals() → usa referência como ==
StringBuilder sb1 = new StringBuilder("abc");
StringBuilder sb2 = new StringBuilder("abc");
System.out.println(sb1.equals(sb2));  // false ❌ (referências diferentes)
System.out.println(sb1 == sb2);       // false ❌

// Para comparar CONTEÚDO de StringBuilders:
System.out.println(sb1.toString().equals(sb2.toString())); // true ✅

// String == StringBuilder → NÃO COMPILA
String s = "a";
StringBuilder sb = new StringBuilder("a");
// System.out.println(s == sb); // DOES NOT COMPILE
// Compilador sabe que nunca podem ser o mesmo objecto
```

> **Regra:** Nunca uses `==` ou `intern()` para comparar Strings em código real.
> Usa sempre `.equals()`. O `==` e `intern()` só existem para o exame!

---

## 5. Arrays

### O que é um Array?
Um array é uma área de memória no **Heap** com espaço para um número fixo de elementos.
É um **objecto** — mesmo `int[]` é um objecto que herda de `Object`.

```
int x = 5;          → primitivo na stack, SEM métodos
int[] x = {1,2,3};  → objecto no heap, TEM métodos (herdados de Object)
                       por isso int[].equals() compila!
                       mas equals() compara REFERÊNCIA (herdado de Object)
```

### Formas de Declarar e Criar

| Sintaxe | Válida? | Nota |
|---|---|---|
| `int[] nums = new int[3];` | ✅ | Elementos inicializados a `0` |
| `int[] nums = new int[]{1,2,3};` | ✅ | Com valores explícitos |
| `int[] nums = {1,2,3};` | ✅ | Anonymous array — mais curto |
| `int nums[]` | ✅ | Válido mas não recomendado |
| `int[] ids, types;` | ✅ | **Dois** `int[]` |
| `int ids[], types;` | ✅ | `ids` é `int[]`, `types` é `int` — armadilha! |

### Valores por Defeito
```java
int[]     nums    = new int[3];    // {0, 0, 0}
String[]  names   = new String[2]; // {null, null}
boolean[] flags   = new boolean[2];// {false, false}
double[]  values  = new double[2]; // {0.0, 0.0}
```

### Uso e Armadilhas
```java
String[] mammals = {"monkey", "chimp", "donkey"};
System.out.println(mammals.length); // 3 — propriedade, SEM ()!
// Diferente de String.length() que TEM ()

// ArrayIndexOutOfBoundsException:
// mammals[3]               → índice 3 não existe (0-2)
// mammals[mammals.length]  → length=3, índice 3 inválido
// for(i=0; i<=length; i++) → <= em vez de <  ← armadilha comum!
```

### Arrays de Referência
```java
String[] bugs = {"cricket", "beetle", "ladybug"};
String[] alias = bugs; // alias aponta para o MESMO array

System.out.println(bugs.equals(alias));    // true  (mesma referência)
System.out.println(bugs == alias);         // true  (mesmo objecto)

// Para imprimir array legível:
System.out.println(Arrays.toString(bugs)); // [cricket, beetle, ladybug]
// bugs.toString() → [Ljava.lang.String;@hashcode  (não útil!)
```

### Sorting
```java
int[] numbers = {6, 9, 1};
Arrays.sort(numbers);
System.out.println(Arrays.toString(numbers)); // [1, 6, 9]

// ARMADILHA: Strings ordenam ALFABETICAMENTE por valor Unicode!
String[] strings = {"10", "9", "100"};
Arrays.sort(strings);
System.out.println(Arrays.toString(strings)); // [10, 100, 9]
// "1" (Unicode 49) vem antes de "9" (Unicode 57) alfabeticamente
// Por isso "10" e "100" vêm antes de "9"!
```

### Binary Search
```java
// Array TEM de estar ordenado!
int[] numbers = {2, 4, 6, 8};
//               0  1  2  3

Arrays.binarySearch(numbers, 2); //  0  → encontrou no índice 0
Arrays.binarySearch(numbers, 4); //  1  → encontrou no índice 1
Arrays.binarySearch(numbers, 1); // -1  → inserir no índice 0 → -(0)-1 = -1
Arrays.binarySearch(numbers, 3); // -2  → inserir no índice 1 → -(1)-1 = -2
Arrays.binarySearch(numbers, 9); // -5  → inserir no índice 4 → -(4)-1 = -5

// Array NÃO ordenado → resultado IMPREVISÍVEL!
int[] unsorted = {3, 2, 1};
Arrays.binarySearch(unsorted, 2); // ⚠️ imprevisível — resposta de exame!
```

**Fórmula quando não encontra:**
```
resultado = -(índice onde devia ser inserido) - 1

O -1 existe para não colidir com índices válidos
(índice 0 já significa "encontrou na posição 0")
```

### Binary Search — Regras

| Cenário | Resultado |
|---|---|
| Elemento encontrado em array ordenado | Índice do match (≥ 0) |
| Elemento não encontrado em array ordenado | `-(índice de inserção) - 1` |
| Array não ordenado | Resultado imprevisível |

### compare() — Algoritmo
O algoritmo tem **dois passos com prioridade**:

```
PASSO 1 — Compara elemento a elemento (valores decimais/Unicode)
          Se encontrar diferença → devolve resultado IMEDIATAMENTE

PASSO 2 — Se todos os elementos comparados forem iguais
          → compara comprimento (nº de posições)
```

```java
// PASSO 1 — diferença nos valores → comprimento ignorado
Arrays.compare(new int[]{1}, new int[]{2});      // negativo (1 < 2)
Arrays.compare(new int[]{5, 1}, new int[]{3, 9});// positivo (5 > 3, para no índice 0!)

// PASSO 2 — valores iguais → comprimento decide
Arrays.compare(new int[]{1, 2}, new int[]{1, 2})); // 0       (iguais)
Arrays.compare(new int[]{1, 2}, new int[]{1});      // positivo (1º array maior)
Arrays.compare(new int[]{1},    new int[]{1, 2});   // negativo (1º array menor)
```

> **Importante:** Não precisas de saber o número exacto devolvido — só o sinal!
> (negativo / zero / positivo)

### Ordem de "menor" no compare()
```
MENOR ──────────────────────────────────► MAIOR

null  →  dígitos  →  MAIÚSCULAS  →  minúsculas
         '0'-'9'     'A'-'Z'        'a'-'z'
          48-57       65-90          97-122
                   (valores Unicode decimal)

"a" vs "aa" → negativo  ("a" é prefixo de "aa" → mais curto é menor)
"a" vs "A"  → positivo  ('a'=97 > 'A'=65)
"1" vs "A"  → negativo  ('1'=49 < 'A'=65)
```

### Unicode e a JVM
```
A JVM converte char → int automaticamente antes de comparar
(widening primitive conversion)

'0' → 48  → 00110000 (binário)
'A' → 65  → 01000001 (binário)
'a' → 97  → 01100001 (binário)

'0' < 'A' → 48 < 65 → true  ✅
'A' < 'a' → 65 < 97 → true  ✅

Por que char/byte/short são promovidos para int?
→ CPUs foram desenhados com word size de 32 bits na era do Java
→ operar com tipos menores exigiria instruções extra
→ JVM promove tudo para int (32 bits) antes de operar

E nos CPUs de 64 bits actuais?
→ x86-64 tem registos de 32 bits (EAX) dentro dos de 64 bits (RAX)
→ Ao escrever em EAX, os 32 bits altos do RAX são zerados AUTOMATICAMENTE
→ É o hardware a resolver o problema — não a JVM!
```

### equals() vs compare() vs mismatch()

| Método | Arrays iguais | Arrays diferentes |
|---|---|---|
| `equals()` | `true` | `false` |
| `compare()` | `0` | número positivo ou negativo |
| `mismatch()` | `-1` | índice do 1º elemento diferente |

```java
Arrays.equals(new int[]{1,2}, new int[]{1,2});    // true
Arrays.compare(new int[]{1,2}, new int[]{1,2});   // 0
Arrays.mismatch(new int[]{1,2}, new int[]{1,2});  // -1

Arrays.mismatch(new int[]{1,2}, new int[]{1});    // 1 (diferem no índice 1)
Arrays.mismatch(new String[]{"a"}, new String[]{"A"}); // 0 (diferem no índice 0)

// DOES NOT COMPILE — tipos diferentes!
// Arrays.compare(new int[]{1}, new String[]{"a"});
```

### Arrays Multidimensionais
```java
int[][] vars1;           // 2D
int[] vars3[];           // 2D — válido mas confuso
// int[] vars4[], space[][];  // vars4=2D, space=3D — armadilha!

String[][] rectangle = new String[3][2]; // 3 linhas, 2 colunas
rectangle[0][1] = "set";
System.out.println(rectangle[0][1]); // "set"
System.out.println(rectangle[1][0]); // null

// Arrays assimétricos — cada linha pode ter tamanho diferente!
int[][] diff = {{1,4}, {3}, {9,8,7}};
System.out.println(diff[0].length); // 2
System.out.println(diff[1].length); // 1
System.out.println(diff[2].length); // 3
```

---

## 6. ArrayList

### Por que ArrayList não aceita primitivos?
```
ArrayList usa Generics → Generics só funcionam com Objectos

List<int>     → ❌ NÃO COMPILA
List<Integer> → ✅ Integer é um objecto (Wrapper Class)

Cada primitivo tem o seu Wrapper:
int      → Integer
double   → Double
boolean  → Boolean
char     → Character
long     → Long
float    → Float
byte     → Byte
short    → Short
```

### Array vs ArrayList

| | `Array` | `ArrayList` |
|---|---|---|
| Tamanho | Fixo | Dinâmico |
| Primitivos? | ✅ Sim | ❌ Não — só objectos |
| Tamanho | `.length` (propriedade) | `.size()` (método) |
| `equals()` | Referência ❌ | Conteúdo ✅ |
| Import | Não necessário | `java.util.ArrayList` |

### Formas de Criar
```java
ArrayList<String> list1 = new ArrayList<>();         // vazio
ArrayList<String> list2 = new ArrayList<>(10);       // capacidade inicial (NÃO é size!)
ArrayList<String> list3 = new ArrayList<>(list1);    // cópia de outra lista

// Forma mais comum — declarar com interface List
List<String> list4 = new ArrayList<>();

// Raw type — sem generics (evitar!)
ArrayList listAntiga = new ArrayList(); // ⚠️ aceita qualquer Object → ClassCastException em runtime!

System.out.println(list2.size()); // 0 ← capacidade 10 ≠ size 0!
```

### Métodos Principais

| Método | O que faz |
|---|---|
| `add(E e)` | Acrescenta ao fim — O(1) |
| `add(int index, E e)` | Insere no índice — desloca os seguintes +1 — O(n) |
| `remove(int index)` | Remove pelo índice — desloca os seguintes -1 |
| `remove(Object o)` | Remove pelo valor |
| `set(int index, E e)` | Substitui — **não desloca nada** — devolve o valor antigo |
| `get(int index)` | Devolve elemento no índice |
| `size()` | Nº de elementos |
| `isEmpty()` | `true` se vazio |
| `clear()` | Remove tudo |
| `contains(Object o)` | Verifica se existe |
| `equals(Object o)` | Compara conteúdo e ordem ✅ |

### add() — Como funciona o deslocamento
```
add(E e) — sem índice → sempre no fim
─────────────────────────────────────
[]  →  ["hawk"]  →  ["hawk","robin"]  →  ["hawk","robin","eagle"]
                                                              ↑
                                                       sempre aqui


add(int index, E e) — com índice → desloca tudo à frente
──────────────────────────────────────────────────────────
Lista: ["hawk", "robin"]
         0        1

birds.add(0, "blue jay"):

ANTES:  ["hawk",    "robin"]
          0           1

         ← todos deslocam +1 →

DEPOIS: ["blue jay", "hawk",  "robin"]
          0            1        2
          ✅ blue jay entra no 0
          ✅ hawk passa de 0 → 1
          ✅ robin passa de 1 → 2
```

### set() vs add() vs remove()
```
set(index, valor)   → SUBSTITUI — não desloca nada, tamanho igual
add(index, valor)   → INSERE    — desloca +1, tamanho aumenta
remove(index)       → ELIMINA   — desloca -1, tamanho diminui

birds.set(1, "eagle"):
ANTES:  ["blue jay", "hawk",  "robin"]
DEPOIS: ["blue jay", "eagle", "robin"]  ← só índice 1 mudou, resto igual

// set() devolve o elemento antigo!
String old = birds.set(0, "eagle"); // old = "hawk"
```

### equals() em ArrayList
```java
// ArrayList.equals() compara CONTEÚDO e ORDEM
List<String> birds1 = new ArrayList<>(Arrays.asList("hawk", "robin"));
List<String> birds2 = new ArrayList<>(Arrays.asList("hawk", "robin"));
List<String> birds3 = new ArrayList<>(Arrays.asList("robin", "hawk")); // ordem diferente!

System.out.println(birds1.equals(birds2)); // true  ✅ (conteúdo e ordem iguais)
System.out.println(birds1.equals(birds3)); // false ❌ (ordem diferente!)
```

### Autoboxing & Unboxing
```
Autoboxing  → int  → Integer  (automático ao adicionar ao ArrayList)
Unboxing    → Integer → int   (automático ao retirar do ArrayList)

numbers.add(1)      → JVM faz: numbers.add(Integer.valueOf(1))
int x = numbers.get(0) → JVM faz: int x = numbers.get(0).intValue()
```

```java
// ARMADILHA CLÁSSICA DO EXAME: remove(int) vs remove(Integer)
List<Integer> numbers = new ArrayList<>();
numbers.add(1); numbers.add(2); numbers.add(3);
// lista: [1, 2, 3]

numbers.remove(1);
// 1 é literal int → Java chama remove(int index)
// remove o elemento no ÍNDICE 1 → remove o valor 2
// lista: [1, 3]

numbers.remove(Integer.valueOf(1));
// Integer.valueOf(1) é um Object → Java chama remove(Object o)
// remove o elemento com VALOR 1
// lista: [3]

// Três formas de remover por VALOR:
numbers.remove(Integer.valueOf(1)); // ✅ explícito
numbers.remove((Integer) 1);        // ✅ cast para Integer
// numbers.remove(1)                // ❌ remove por índice!
```

> **Por que não há autoboxing no remove(1)?**
> Porque já existe a assinatura `remove(int index)` que encaixa directamente.
> O autoboxing só acontece quando **não existe** assinatura com o tipo primitivo!

### Raw Type — Perigo!
```java
List list = new ArrayList(); // raw type — sem generics
list.add("hawk");            // String ✅
list.add(1);                 // Integer ✅ (autoboxing)
list.add(true);              // Boolean ✅

// Compilador não te protege — ClassCastException em runtime!
String s = (String) list.get(1); // 💥 ClassCastException!
// list.get(1) devolve Integer, não String
```

### Conversão ArrayList ↔ Array
```java
// ArrayList → Array
List<String> list = new ArrayList<>(Arrays.asList("hawk", "robin"));

Object[] objectArray = list.toArray();               // Object[] (perde tipo)
String[] stringArray = list.toArray(new String[0]);  // String[] ✅

// Por que new String[0]?
// → É uma "dica de tipo" para o Java saber que queres String[]
// → O tamanho 0 é irrelevante — Java cria array do tamanho certo internamente
// → Forma moderna e recomendada
// → Forma antiga: list.toArray(new String[list.size()]) — também funciona

// Array → ArrayList
String[] array = {"hawk", "robin"};

List<String> fixedList = Arrays.asList(array); // ⚠️ TAMANHO FIXO!
fixedList.set(0, "eagle");  // ✅ substituir é permitido
// fixedList.add("falcon"); // 💥 UnsupportedOperationException
// fixedList.remove(0);     // 💥 UnsupportedOperationException

// Java 9+ — List.of() → completamente imutável
List<String> immutable = List.of("hawk", "robin");
// immutable.set(0, "x"); // 💥 UnsupportedOperationException
// immutable.add("x");    // 💥 UnsupportedOperationException
// immutable.remove(0);   // 💥 UnsupportedOperationException
```

**Resumo:**
```
Arrays.asList() → tamanho FIXO
                  set()    ✅  substituir permitido
                  add()    💥  exception
                  remove() 💥  exception

List.of()       → completamente IMUTÁVEL
                  set()    💥  exception
                  add()    💥  exception
                  remove() 💥  exception
```

### ArrayList vs LinkedList — Performance
```
ArrayList internamente = array contíguo na memória
LinkedList internamente = nós ligados por referências (ponteiros)

ArrayList — add(0, "x"):
["hawk", "robin", "eagle"]
  TODOS os elementos deslocam +1 → O(n) ❌ lento

LinkedList — addFirst("x"):
[blue jay] → [hawk] → [robin] → [eagle]
Só muda 2 referências → O(1) ✅ rápido
(não precisa deslocar nada!)

ArrayList — get(index):
Acesso directo ao endereço de memória → O(1) ✅ rápido

LinkedList — get(index):
Percorre nó a nó desde o início → O(n) ❌ lento
```

| Operação | ArrayList | LinkedList |
|---|---|---|
| `get(index)` | O(1) ✅ rápido | O(n) ❌ lento |
| `add()` no fim | O(1) ✅ rápido | O(1) ✅ rápido |
| `add()` no início/meio | O(n) ❌ lento | O(1) ✅ rápido |
| `remove()` no início/meio | O(n) ❌ lento | O(1) ✅ rápido |
| Memória | Menos | Mais (2 referências por nó) |

> **Na prática:** ArrayList é o mais usado porque `get()` rápido é mais valioso
> na maioria dos casos. LinkedList quando inserções/remoções no início/meio são frequentes.

### Sorting
```java
List<Integer> numbers = new ArrayList<>(Arrays.asList(99, 5, 81));
Collections.sort(numbers);         // para ArrayList → Collections.sort()
System.out.println(numbers);       // [5, 81, 99]

int[] array = {6, 9, 1};
Arrays.sort(array);                 // para arrays → Arrays.sort()
System.out.println(Arrays.toString(array)); // [1, 6, 9]
```

---

## 7. Math APIs

> Todos os métodos são **`static`** — nunca instancias `new Math()`!

### Métodos do Exame

| Método | Devolve | O que faz |
|---|---|---|
| `Math.min(a, b)` | mesmo tipo dos args | Devolve o menor |
| `Math.max(a, b)` | mesmo tipo dos args | Devolve o maior |
| `Math.round(d)` | **`long`** | Arredonda ao inteiro mais próximo |
| `Math.pow(base, exp)` | **`double`** | Potência |
| `Math.random()` | **`double`** | Número aleatório `[0.0, 1.0[` |

```java
// min() e max() — cuidado com negativos!
Math.min(7, 3);      // 3
Math.max(7, 3);      // 7
Math.min(-5, -10);   // -10 ← -10 é MENOR que -5 na linha numérica!
Math.max(-5, -10);   // -5  ← -5 é MAIOR que -10

// round() — arredonda .5 sempre para CIMA
Math.round(3.5);     // 4
Math.round(3.4);     // 3
Math.round(-3.5);    // -3  ← ARMADILHA! para CIMA em negativos = menos negativo
Math.round(-3.6);    // -4

// Linha numérica para entender o round() em negativos:
// ◄────────────────────────────────►
// -4    -3.5    -3    3.5    4
//         ↑             ↑
//       vai -3        vai 4
//       (cima)        (cima)

// ARMADILHA: round() devolve long, NÃO double!
long result = Math.round(9.99);    // 10 ✅
// double d = Math.round(9.99);    // ❌ NÃO COMPILA sem cast!

// pow() — devolve sempre double
Math.pow(2, 3);      // 8.0  → 2³
Math.pow(3, 2);      // 9.0  → 3²
Math.pow(4, 0.5);    // 2.0  → raiz quadrada de 4!

// random() — [0.0, 1.0[  (0.0 incluído, 1.0 excluído)
double r = Math.random();
System.out.println(r >= 0.0 && r < 1.0); // sempre true!

// Padrões comuns:
int zeroANove = (int)(Math.random() * 10);    // 0 a 9
int umADez    = (int)(Math.random() * 10) + 1; // 1 a 10
```

---

## 8. Pontos-Bomba para o Exame

### String
- `length()` conta de **1**; `charAt()`/`indexOf()` contam de **0**
- `substring(3,3)` → string vazia `""` — **não é exception!**
- `substring(3,2)` → **exception** (begin > end)
- `strip()` é Java 11 e suporta Unicode; `trim()` não
- Cada método String cria um **novo objecto** — String é imutável!
- `contains("b")` é equivalente a `indexOf("b") != -1`

### StringBuilder
- `new StringBuilder()` chamado uma vez → **um único objecto sempre**
- `delete(1, 100)` com end fora do range → **não dá exception**, trunca ao fim
- `deleteCharAt(index fora do range)` → **dá exception**
- `substring()` no StringBuilder devolve `String` — **não modifica o StringBuilder**
- `StringBuilder` **não implementa `equals()`** → usa referência como `==`
- Para comparar conteúdo de dois StringBuilders: `sb1.toString().equals(sb2.toString())`

### String Pool
- Literal → pool; `new String()` → Eden Space; `intern()` → força pool
- `"a" + "b"` em compile-time → pool; `str1 + str2` em runtime → não vai
- `new String("Hello")` → **sempre** fora do pool → `==` dá `false`
- Nunca usar `==` ou `intern()` em código real — só para o exame!
- Desde Java 7 o pool está **dentro do Heap** (antes ficava em PermGen)

### Arrays
- `length` é **propriedade** (sem `()`) — diferente de `String.length()`
- `int[] ids, types` → **dois** `int[]`; `int ids[], types` → um `int[]` e um `int`
- `Arrays.sort()` em Strings ordena **alfabeticamente** — `"9"` depois de `"100"`!
- `binarySearch()` em array não ordenado → **resultado imprevisível**
- `compare()` retorna `0` se iguais; `mismatch()` retorna `-1` se iguais — não confundir!
- `Arrays.equals()` para conteúdo; `array.equals()` para referência
- `int[]` é um **Object** — tem métodos; `int` é primitivo — não tem métodos
- `Arrays.compare()` com tipos diferentes → **não compila**

### ArrayList
- `new ArrayList<>(10)` → capacidade inicial, **`size()` continua `0`**
- `remove(1)` → índice; `remove(Integer.valueOf(1))` → valor — armadilha clássica!
- `Arrays.asList()` → tamanho **fixo** — `set()` ok, `add()`/`remove()` exception!
- `List.of()` → completamente **imutável** — nada funciona!
- `Collections.sort()` para ArrayList; `Arrays.sort()` para arrays — não confundir!
- `ArrayList.equals()` compara conteúdo **e ordem** — ordem diferente → `false`!
- Raw type `List list` → perigo de `ClassCastException` em runtime

### Math
- `Math.round(-3.5)` → `-3` (para cima mesmo em negativos!)
- `Math.round()` devolve **`long`**, não `double`
- `Math.random()` → `0.0` incluído, `1.0` excluído → `[0.0, 1.0[`
- `Math.pow()` devolve sempre **`double`** — `Math.pow(2,3)` = `8.0` não `8`
- Todos os métodos são **`static`** — nunca `new Math()`!

---

## Resultado da Prova (Estilo NatWest/Barclays/HSBC)
**7/10** — Abril 2026

| Questão | Tema | Resultado |
|---|---|---|
| 1 | Concatenação de Strings | ✅ |
| 2 | substring() | ❌ |
| 3 | Imutabilidade / contar objectos String | ❌ |
| 4 | StringBuilder mutabilidade | ✅ |
| 5 | String Pool / intern() | ❌ |
| 6 | Binary Search | ❌ |
| 7 | ArrayList remove() autoboxing | ✅ |
| 8 | Arrays.asList() tamanho fixo | ✅ |
| 9 | Math.round() negativos | ✅ |
| 10 | compare() / mismatch() | ✅ |

---

*Estudado em: Abril 2026*
*Livro: OCP Java SE 11 Programmer I — Boyarsky & Selikoff (Sybex, 2020)*
