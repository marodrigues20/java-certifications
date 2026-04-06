# OCP Java 11 Programmer I — Chapter 4: Making Decisions
## if, switch, loops, break, continue e labels

---

## 1. Statements e Blocks

Um **statement** é uma instrução completa terminada com `;`.
Um **block** é um grupo de zero ou mais statements entre `{}`.

```java
// Statement simples
patrons++;

// Mesmo dentro de um block — equivalente
{
    patrons++;
}
```

---

## 2. O `if` Statement

Executa um bloco **apenas se** a condição booleana for `true`.

```java
if (hourOfDay < 11) {
    System.out.println("Good Morning");
} else if (hourOfDay < 15) {
    System.out.println("Good Afternoon");
} else {
    System.out.println("Good Evening");
}
```

### Armadilhas do exame

**1. Indentação sem `{}`— só o primeiro statement é controlado pelo `if`:**
```java
if (hourOfDay < 11)
    System.out.println("Good Morning");    // controlado pelo if
    System.out.println("This ALWAYS runs"); // SEMPRE executa!
```

**2. A condição TEM de ser `boolean`:**
```java
int x = 1;
// if (x) { }       // DOES NOT COMPILE — int não é boolean
// if (x = 5) { }   // DOES NOT COMPILE — assignment não é boolean
if (x == 5) { }     // OK
```

**3. Ordem errada cria branch UNREACHABLE:**
```java
if (hourOfDay < 15) {
    System.out.println("Afternoon");
} else if (hourOfDay < 11) {
    System.out.println("Morning"); // UNREACHABLE — compila mas nunca executa
}
```

---

## 3. O `switch` Statement

Avalia um único valor e redireciona o fluxo para o `case` correspondente.

### Tipos suportados

| Tipo                                   | Suportado? |
|----------------------------------------|------------|
| `int` / `Integer`                      | ✅          |
| `byte` / `Byte`                        | ✅          |
| `short` / `Short`                      | ✅          |
| `char` / `Character`                   | ✅          |
| `String`                               | ✅          |
| `enum`                                 | ✅          |
| `var` (se resolve a um dos anteriores) | ✅          |
| `boolean` / `long` / `float` / `double`| ❌          |

### Sintaxe — armadilhas do exame

```java
switch month { }            // DOES NOT COMPILE — faltam parênteses
switch (month)              // DOES NOT COMPILE — faltam {}
   case 1: ...

switch (month) {
   case 1: 2: ...           // DOES NOT COMPILE — falta keyword case antes do 2
}
switch (month) {
   case 1 || 2: ...         // DOES NOT COMPILE — || não é válido em case
}
switch (month) {}           // OK — switch sem cases é válido
```

### Fall-through — armadilha mais frequente no exame

Sem `break`, o fluxo **continua** para o próximo `case`:

```java
int day = 5;
switch (day) {
    case 0:
        System.out.println("Sunday");
    default:
        System.out.println("Weekday");  // executa (sem break acima)
    case 6:
        System.out.println("Saturday"); // também executa
        break;
}
// Output: Weekday / Saturday
```

> ⚠️ O `default` só é visitado quando **nenhum** `case` corresponde.
> Mas se o fluxo já estiver a passar por ele (fall-through), executa normalmente.

### Valores válidos em `case`

| Tipo de valor                                  | Válido? |
|------------------------------------------------|---------|
| Literal (`"Test"`, `1`, `'A'`)                 | ✅       |
| `final` variável inicializada na declaração    | ✅       |
| Expressão resolvível em compile-time (`3 * 5`) | ✅       |
| Variável não-`final`                           | ❌       |
| Resultado de método (`getCookies()`)           | ❌       |
| Parâmetro de método (mesmo `final`)            | ❌       |

### Numeric Promotion em `case`

O valor do `case` não precisa ser do mesmo tipo que o switch — mas tem de **caber** no tipo sem cast explícito:

```java
short size = 4;
final int small = 15;
final int big = 1_000_000;

switch (size) {
    case small:   // OK — 15 cabe em short
    case 1 + 2:   // OK — 3 cabe em short
    case big:     // DOES NOT COMPILE — 1_000_000 não cabe em short
}
```

---

## 4. O `while` Loop

Executa **zero ou mais vezes** — condição verificada **antes** de cada iteração.

```java
int full = 5;
while (full < 5) {
    System.out.println("Not full!"); // nunca executa
    full++;
}
```

---

## 5. O `do/while` Loop

Executa **uma ou mais vezes** — corpo executa **primeiro**, condição verificada **depois**.

```java
int lizard = 0;
do {
    lizard++;
} while (false); // executa uma vez mesmo com false

System.out.println(lizard); // 1
```

### Comparação `while` vs `do/while`

| | `while` | `do/while` |
|---|---|---|
| Mínimo de execuções | 0 | 1 |
| Condição verificada | antes | depois |
| Semicolon no fim | não | **sim** (obrigatório) |

---

## 6. O `for` Loop

Três secções separadas por `;`: inicialização, condição, update.

```java
for (int i = 0; i < 5; i++) {
    System.out.print(i + " "); // 0 1 2 3 4
}
```

### Regras importantes

- As 3 secções são **opcionais** — `for( ; ; )` compila e cria loop infinito
- Múltiplas variáveis na inicialização: separadas por `,` mas **do mesmo tipo**
- Variável declarada dentro do `for` → só existe dentro do loop
- Variável declarada fora do `for` → continua acessível depois do loop

```java
// Múltiplas variáveis — mesmo tipo
for (int i = 0, j = 10; i < 3; i++, j--) { }

// DOES NOT COMPILE — tipos diferentes
// for (int i = 0, long j = 10; i < 3; i++) { }

// Variável fora do for — acessível depois
int i = 0;
for (i = 0; i < 3; i++) { }
System.out.println(i); // 3 — ainda acessível!
```

---

## 7. O `for-each` Loop

Desenhado para iterar sobre **arrays** e **Collections** sem gerir índices.

```java
String[] names = {"Lisa", "Kevin", "Roger"};
for (String name : names) {
    System.out.print(name + ", ");
}
```

### Limitações vs `for` normal

| | `for` | `for-each` |
|---|---|---|
| Controlo de índice | ✅ | ❌ |
| Iterar ao contrário | ✅ | ❌ |
| Modificar o array original | ✅ | ❌ |
| Legibilidade | menor | **maior** |

> ⚠️ Modificar a variável do loop **não altera o array original** — é uma cópia local.

---

## 8. Nested Loops

Um loop dentro de outro. O loop interior completa **todas** as suas iterações para cada iteração do loop exterior.

```java
for (int i = 0; i < 3; i++) {      // exterior — 3 iterações
    for (int j = 0; j < 3; j++) {  // interior — 3 iterações por cada i
        System.out.print(i * j + "\t");
    }
    System.out.println();
}
```

### Array 2D

```java
int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};

for (int i = 0; i < matrix.length; i++) {        // matrix.length    → nº de linhas
    for (int j = 0; j < matrix[i].length; j++) { // matrix[i].length → nº de colunas da linha i
        System.out.print(matrix[i][j] + " ");
    }
}
```

---

## 9. `break`, `continue` e Labels

| Statement        | O que faz                                          |
|------------------|----------------------------------------------------|
| `break`          | Sai do loop/switch imediatamente                   |
| `continue`       | Salta para a próxima iteração do loop              |
| `break label`    | Sai do loop **exterior** identificado pelo label   |
| `continue label` | Salta para a próxima iteração do loop **exterior** |

```java
// break com label — sai dos DOIS loops
PARENT_LOOP:
for (int i = 0; i < list.length; i++) {
    for (int j = 0; j < list[i].length; j++) {
        if (list[i][j] == searchValue) {
            break PARENT_LOOP; // sai do loop exterior
        }
    }
}

// continue com label — salta para próxima iteração do loop exterior
OUTER_LOOP:
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) continue OUTER_LOOP; // salta para próximo i
        System.out.println("i=" + i + " j=" + j);
    }
}
```

> **Sem label** → afecta apenas o loop **mais interior**.
> **Com label** → afecta o loop **identificado pelo label**.

---

## 10. Unreachable Code

Qualquer código colocado **imediatamente após** `break`, `continue` ou `return` no mesmo bloco — o compilador rejeita.

```java
for (int i = 0; i < 5; i++) {
    if (i == 3) {
        break;
        System.out.println("aqui"); // DOES NOT COMPILE — unreachable!
    }
}

// Fora do bloco — é alcançável, compila normalmente
System.out.println("here"); // OK
```

---

## Resumo Final — Regras de Ouro

| Conceito | Regra |
|---|---|
| `if` | condição DEVE ser `boolean` |
| `switch` | não suporta `boolean`, `long`, `float`, `double` |
| `case` | só aceita compile-time constants |
| Fall-through | sem `break`, fluxo continua para o próximo `case` |
| `while` | executa 0 ou mais vezes |
| `do/while` | executa 1 ou mais vezes — semicolon obrigatório |
| `for` sem secções | `for( ; ; )` é válido — loop infinito |
| `for-each` | não modifica o array original |
| `break`/`continue` sem label | afecta loop mais interior |
| `break`/`continue` com label | afecta loop identificado pelo label |
| Unreachable code | código após `break`/`continue`/`return` no mesmo bloco não compila |
