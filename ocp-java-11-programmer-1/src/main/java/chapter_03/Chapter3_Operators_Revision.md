# OCP Programmer I — Chapter 3: Operators
**Boyarsky & Selikoff (Sybex, 2020) — Java SE 11**
**Revisão completa para certificação**

---

## 1. Tipos de Operadores

| Tipo | Operandos | Exemplo |
|------|-----------|---------|
| Unário | 1 | `++a`, `!b` |
| Binário | 2 | `a + b` |
| Ternário | 3 | `a ? b : c` |

---

## 2. Operator Precedence (Tabela 3.1)

> Se dois operadores têm a mesma precedência → Java avalia da **esquerda para a direita**.

| # | Grupo | Operadores |
|---|-------|------------|
| 1 | Post-unary | `expression++` `expression--` |
| 2 | Pre-unary | `++expression` `--expression` |
| 3 | Other unary | `-` `!` `~` `+` `(type)` |
| 4 | Multiplicação | `*` `/` `%` |
| 5 | Adição | `+` `-` |
| 6 | Shift *(não testado)* | `<<` `>>` `>>>` |
| 7 | Relacional | `<` `>` `<=` `>=` `instanceof` |
| 8 | Igualdade | `==` `!=` |
| 9 | Logical | `&` `^` `\|` |
| 10 | Short-circuit | `&&` `\|\|` |
| 11 | Ternário | `boolean ? expr1 : expr2` |
| 12 | Assignment | `=` `+=` `-=` `*=` `/=` `%=` ... |

---

## 3. Unary Operators

| Operador | Descrição | Aplica-se a |
|----------|-----------|-------------|
| `!` | Inverte boolean | `boolean` apenas |
| `-` | Nega expressão numérica | numéricos apenas |
| `+` | Positivo (implícito) | numéricos |
| `++` | Incrementa 1 | numéricos |
| `--` | Decrementa 1 | numéricos |
| `(type)` | Cast | qualquer tipo |

### ⚠️ Regra crítica:
> Em Java `1` e `true` **não têm relação**. Não podes misturar numéricos com booleanos.

```java
int pelican   = !5;      // DOES NOT COMPILE
boolean peng  = -true;   // DOES NOT COMPILE
boolean peace = !0;      // DOES NOT COMPILE
```

---

## 4. Pre vs Post Increment/Decrement

| Tipo | Sintaxe | Comportamento |
|------|---------|--------------|
| Pre-increment | `++a` | Incrementa **primeiro**, devolve **novo** valor |
| Post-increment | `a++` | Devolve **valor original**, incrementa **depois** |
| Pre-decrement | `--a` | Decrementa **primeiro**, devolve **novo** valor |
| Post-decrement | `a--` | Devolve **valor original**, decrementa **depois** |

```java
int parkAttendance = 0;
System.out.println(++parkAttendance); // 1 — pre:  incrementa ANTES
System.out.println(parkAttendance--); // 1 — post: imprime ANTES de decrementar
System.out.println(parkAttendance);   // 0
```

### Exemplo avançado do exame:
```java
int lion  = 3;
int tiger = ++lion * 5 / lion--;
// Passo 1: ++lion  → lion=4, usa 4  →  4 * 5 / lion--
// Passo 2: lion--  → usa 4, lion=3  →  4 * 5 / 4
// Passo 3: 20 / 4 = 5
System.out.println("lion  = " + lion);  // 3
System.out.println("tiger = " + tiger); // 5
```

---

## 5. Binary Arithmetic Operators

| Operador | Descrição |
|----------|-----------|
| `+` | Adição |
| `-` | Subtracção |
| `*` | Multiplicação |
| `/` | Divisão — devolve **floor** (não arredonda!) |
| `%` | Módulo — devolve o **resto** da divisão |

### Divisão inteira (floor):
```java
System.out.println(10 / 3); // 3  (não 3.33!)
System.out.println(10 % 3); // 1  (resto)
System.out.println(11 / 3); // 3
System.out.println(11 % 3); // 2
```

### Parênteses inválidos — não compilam:
```java
long pigeon = 1 + ((3 * 5) / 3;    // DOES NOT COMPILE — não balanceados
short robin = 3 + [(4 * 2) + 4];   // DOES NOT COMPILE — [] não permitidos
```

---

## 6. Numeric Promotion Rules (4 Regras)

| # | Regra |
|---|-------|
| 1 | Tipos diferentes → Java promove o **menor** para o **maior** |
| 2 | Integral + floating-point → integral promovido para floating-point |
| 3 | `byte`, `short`, `char` → sempre promovidos para **`int`** com operadores binários |
| 4 | Resultado tem o **mesmo tipo** dos operandos promovidos |

### ⚠️ Regra 3 — a mais perigosa no exame:
```java
short a = 10;
short b = 3;
short resultado = a * b; // DOES NOT COMPILE — resultado é int!
short correcto  = (short)(a * b); // ✅ cast obrigatório
```

### Hierarquia de promoção:
```
double  ← maior
  ↑
float
  ↑
long
  ↑
int  ← byte, short, char sempre promovidos até aqui
```

### Overflow e Underflow:
```java
System.out.println(2147483647 + 1); // -2147483648 (overflow!)
// O overflow acontece NO OPERADOR — não no destino!
// Ambos os operandos são int → cálculo em int → overflow → resultado já "rebentado"

// Solução: garantir que pelo menos UM operando é long ANTES da operação
System.out.println(2147483647 + 1L); //  2147483648 ✅
System.out.println((long)2147483647 + 1); //  2147483648 ✅
System.out.println((long)(2147483647 + 1)); // -2147483648 ❌ overflow já aconteceu!
```

---

## 7. Assignment Operator + Casting

### Widening (automático) vs Narrowing (cast obrigatório):

| Situação | Casting necessário? |
|----------|-------------------|
| `int` → `long` | ❌ Não — promoção automática |
| `long` → `int` | ✅ Sim — narrowing |
| `double` → `int` | ✅ Sim — narrowing |
| `int` → `double` | ❌ Não — promoção automática |

### ⚠️ Cast é operador UNÁRIO — aplica-se só ao operando imediato:
```java
int tadpole = (int)5 * 2L;      // DOES NOT COMPILE — cast só no 5, resultado é long!
int correcto = (int)(5 * 2L);   // ✅ cast envolve a expressão toda
```

### Exemplos que não compilam:
```java
float egg    = 2.0 / 9;     // DOES NOT COMPILE — 2.0 é double!
int tadpole  = (int)5 * 2L; // DOES NOT COMPILE — resultado é long!
short frog   = 3 - 2.0;     // DOES NOT COMPILE — resultado é double!
```

### Casting com short:
```java
short mouse   = 10;
short hamster = 3;
short capybara = mouse * hamster;         // DOES NOT COMPILE — resultado é int!
short capybara = (short)(mouse * hamster); // ✅

// Cast só no mouse NÃO chega:
short errado = (short)mouse * hamster;    // DOES NOT COMPILE
// Cast é unário → aplica ao mouse → depois * promove tudo para int novamente!
```

---

## 8. Compound Assignment Operators

| Operador | Equivalente a |
|----------|--------------|
| `+=` | `a = a + b` |
| `-=` | `a = a - b` |
| `*=` | `a = a * b` |
| `/=` | `a = a / b` |

### ⚡ Super poder: cast automático!
```java
long goat  = 10;
int  sheep = 5;

sheep = sheep * goat; // DOES NOT COMPILE — long não cabe em int!
sheep *= goat;        // ✅ compound faz o cast para int automaticamente!
```

### Assignment Return Value — armadilha do exame:
```java
long wolf   = 5;
long coyote = (wolf = 3); // wolf=3, coyote=3 — assignment devolve o valor!

boolean healthy = false;
if (healthy = true)        // ASSIGNMENT não COMPARAÇÃO! — entra sempre!
    System.out.println("Good!");
```

---

## 9. Equality Operators

| Operador | Primitivos | Objectos |
|----------|-----------|---------|
| `==` | `true` se valores **iguais** | `true` se **mesmo objecto** em memória |
| `!=` | `true` se valores **diferentes** | `true` se **objectos diferentes** |

### 3 casos válidos para `==`:
1. Dois primitivos numéricos
2. Dois `boolean`
3. Dois objectos (incluindo `null` e `String`)

```java
String s1 = new String("Java");
String s2 = new String("Java");
String s3 = s1;

System.out.println(s1 == s2);      // false — objectos diferentes em memória!
System.out.println(s1 == s3);      // true  — mesmo objecto!
System.out.println(s1.equals(s2)); // true  — conteúdo igual!
System.out.println(null == null);  // true

// DOES NOT COMPILE:
boolean monkey = true == 3;        // DOES NOT COMPILE
boolean ape    = false != "Grape"; // DOES NOT COMPILE
```

### ⚠️ `=` vs `==` — armadilha clássica:
```java
if (healthy = true)   // ASSIGNMENT — atribui true, entra sempre!
if (healthy == true)  // COMPARAÇÃO — depende do valor de healthy
```

---

## 10. Relational Operators

| Operador | Descrição |
|----------|-----------|
| `<` | Estritamente menor que |
| `<=` | Menor ou igual |
| `>` | Estritamente maior que |
| `>=` | Maior ou igual |
| `instanceof` | Verifica se objecto é instância de uma classe/interface |

### instanceof — regras críticas:
```java
// null instanceof → sempre false (nunca explode!)
String texto = null;
System.out.println(texto instanceof String); // false ✅

// null instanceof null → DOES NOT COMPILE
System.out.println(null instanceof null);    // DOES NOT COMPILE ❌

// Tipos incompatíveis → DOES NOT COMPILE
Number time = Integer.valueOf(9);
if (time instanceof String) {}              // DOES NOT COMPILE ❌
```

### Padrão clássico — instanceof + cast:
```java
// ✅ Sempre verifica antes de fazer cast!
Number n = Integer.valueOf(42);
if (n instanceof Integer) {
    Integer i = (Integer) n; // cast seguro!
    System.out.println(i);
}
```

---

## 11. Logical Operators

| Operador | Nome | Regra |
|----------|------|-------|
| `&` | AND | `true` só se **ambos** forem `true` |
| `\|` | Inclusive OR | `true` se **pelo menos um** for `true` |
| `^` | Exclusive XOR | `true` só se forem **diferentes** |

### Tabela da verdade:

| `x` | `y` | `x & y` | `x \| y` | `x ^ y` |
|-----|-----|---------|---------|---------|
| true | true | true | true | false |
| true | false | false | true | true |
| false | true | false | true | true |
| false | false | false | false | false |

### Dica para memorizar:
```
&  (AND) → só true se os DOIS forem true
|  (OR)  → só false se os DOIS forem false
^  (XOR) → só true se forem DIFERENTES
```

### ⚠️ `&` e `|` avaliam SEMPRE os dois lados:
```java
int x = 5;
boolean r = (x > 3) | (++x > 4); // ++x É avaliado!
System.out.println(x);            // 6 — x foi incrementado!
```

---

## 12. Short-Circuit Operators

| Operador | Comportamento |
|----------|--------------|
| `&&` | Para se lado esquerdo for **`false`** — resultado já é `false` |
| `\|\|` | Para se lado esquerdo for **`true`** — resultado já é `true` |

### Diferença crítica `&` vs `&&`:
```java
int x = 5;
boolean r1 = (x > 3) | (++x > 4);  // | avalia os dois lados → x = 6
x = 5;
boolean r2 = (x > 3) || (++x > 4); // || para no esquerdo    → x = 5
```

### Evitar NullPointerException:
```java
String duck = null;

// ❌ Perigoso — & avalia os dois lados!
if (duck != null & duck.getClass() != null) {} // NullPointerException!

// ✅ Seguro — && para se duck for null!
if (duck != null && duck.getClass() != null) {} // seguro ✅
```

### Unperformed side effect — armadilha do exame:
```java
int rabbit = 6;
boolean bunny = (rabbit >= 6) || (++rabbit <= 7);
System.out.println(rabbit); // 6 — ++rabbit NUNCA executado! (short-circuit)
```

---

## 13. Ternary Operator `? :`

### Estrutura:
```
booleanExpression ? expression1 : expression2
        ↓                ↓              ↓
    condição         se true        se false
```

### Equivalente a `if/else`:
```java
// if/else
int food;
if (owl < 2) { food = 3; } else { food = 4; }

// Ternário — equivalente!
int food = owl < 2 ? 3 : 4;
```

### Regras críticas:
```java
int stripes = 7;

// ✅ COMPILA — println aceita Object
System.out.print((stripes > 5) ? 21 : "Zebra");

// ❌ DOES NOT COMPILE — "Horse" não é int!
int animal = (stripes < 9) ? 3 : "Horse";
```

### Unperformed side effect:
```java
int sheep = 1, zzz = 1;

// condição true → só sheep++ avaliado!
int sleep = zzz < 10 ? sheep++ : zzz++;
System.out.println("sheep=" + sheep); // 2
System.out.println("zzz="  + zzz);   // 1 — NÃO incrementado!
```

---

## 14. Data Types — Referência Rápida

| Tipo | Bits | Range |
|------|------|-------|
| `byte` | 8 | -128 a 127 |
| `short` | 16 | -32.768 a 32.767 |
| `int` | 32 | -2.147.483.648 a 2.147.483.647 |
| `long` | 64 | -9.2×10¹⁸ a 9.2×10¹⁸ |
| `float` | 32 | ~7 dígitos decimais |
| `double` | 64 | ~15 dígitos decimais |
| `char` | 16 | 0 a 65.535 (sem sinal — tabela Unicode) |
| `boolean` | — | `true` / `false` |

---

## 15. char e Unicode — Conceitos Extra

### Bases numéricas em Java:

| Prefixo | Base | Exemplo | Resultado |
|---------|------|---------|-----------|
| nada | Decimal (10) | `65` | `'A'` |
| `0x` | Hexadecimal (16) | `0x41` | `'A'` |
| `0b` | Binário (2) | `0b1000001` | `'A'` |
| `0` | Octal (8) | `0101` | `'A'` |
| `\uXXXX` | Notação Unicode | `'\u0041'` | `'A'` |

### CodePoint:
> Nome técnico para o número único de cada carácter na tabela Unicode.
> Pode ser representado em qualquer base numérica.

### char vs codePoint:
```
char       → aguenta até 65.535    (16 bits)
codePoint  → aguenta até 1.114.111 (21 bits)

// Emoji não cabe num char → precisa de 2 chars (surrogate pair)!
String emoji = "😀";
System.out.println(emoji.length());          // 2 (dois chars!)
System.out.println(emoji.codePointCount(0,
                   emoji.length()));          // 1 (um emoji!)
```

### UTF-8 vs UTF-16 vs UTF-32:
> **Não são versões do Unicode** — são formas de **codificar** (guardar em bytes) os codePoints.
> A tabela Unicode é **uma só**!

| Encoding | Bytes por carácter | Usado em |
|----------|--------------------|---------|
| UTF-8 | 1-4 bytes (variável) | Web, JSON, Linux |
| UTF-16 | 2-4 bytes + BOM | Java, Windows |
| UTF-32 | 4 bytes (fixo) | Sistemas internos |

---

## 16. Pattern Matching (Java 16+/21) — Bónus

### Java 11 vs Java 16 vs Java 21:

```java
// Java 11 — verbose
if (obj instanceof Integer) {
    Integer i = (Integer) obj; // cast manual!
    System.out.println(i * 2);
}

// Java 16 — Pattern Matching
if (obj instanceof Integer i) { // cast automático!
    System.out.println(i * 2);
}

// Java 21 — switch Pattern Matching
String resultado = switch (obj) {
    case Integer i when i < 0   -> "Negativo: " + i;
    case Integer i when i < 100 -> "Pequeno: "  + i;
    case Integer i              -> "Grande: "   + i;
    case String  s              -> "String: "   + s;
    case null                   -> "É null!";
    default                     -> "Outro tipo";
};
```

### Switch clássico vs Pattern Matching:
| | Switch Clássico | Switch Pattern Matching |
|-|----------------|------------------------|
| Compara | Valores primitivos + String | Instâncias de classes |
| Sintaxe | `case X:` + `break` | `case X ->` sem break |
| Null | ❌ NullPointerException | ✅ `case null ->` |
| Misturável | ❌ Não | ❌ Não |

---

## Exam Essentials — Resumo Final

1. **Operator Precedence** — `*`, `/`, `%` antes de `+`, `-`. Assignment por último.
2. **Promoção Numérica** — `byte`/`short`/`char` sempre promovidos para `int`.
3. **Overflow** — acontece no operador, não no destino!
4. **Cast** — é unário, aplica-se só ao operando imediato.
5. **Compound operators** — fazem cast automático para o tipo da variável esquerda.
6. **`=` vs `==`** — assignment devolve o valor atribuído!
7. **`instanceof null`** — sempre `false`. `null instanceof null` — não compila!
8. **`&` vs `&&`** — `&` avalia sempre os dois lados. `&&` faz short-circuit.
9. **Unperformed side effects** — com `||`, `&&` e `? :` o lado não avaliado **nunca executa**!
10. **Ternário** — tipos das expressões devem ser compatíveis com a variável de destino.
