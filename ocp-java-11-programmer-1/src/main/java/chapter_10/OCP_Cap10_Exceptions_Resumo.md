# OCP Java SE 11 Programmer I — Chapter 10: Exceptions
> Boyarsky & Selikoff (Sybex, 2020)

---

## Índice
1. [A Hierarquia de Exceções](#1-a-hierarquia-de-exceções)
2. [RuntimeException Classes](#2-runtimeexception-classes)
3. [Checked Exceptions & Error Classes](#3-checked-exceptions--error-classes)
4. [throw vs throws](#4-throw-vs-throws)
5. [try/catch — Sintaxe e Fluxo](#5-trycatch--sintaxe-e-fluxo)
6. [Chaining catch Blocks](#6-chaining-catch-blocks)
7. [Multi-catch Block](#7-multi-catch-block)
8. [Bloco finally](#8-bloco-finally)
9. [Try-with-Resources](#9-try-with-resources)
10. [Calling Methods + Override com Exceptions](#10-calling-methods--override-com-exceptions)
11. [Printing an Exception](#11-printing-an-exception)

---

## 1. A Hierarquia de Exceções

### Árvore de herança

```
java.lang.Object
    └── java.lang.Throwable
            ├── java.lang.Exception
            │       └── java.lang.RuntimeException   ← unchecked
            └── java.lang.Error                       ← unchecked
```

### As 3 categorias

| Tipo | Como reconhecer | Obrigatório tratar/declarar? | Pode capturar? |
|---|---|---|---|
| Runtime exception | Subclasse de `RuntimeException` | Não | Sim |
| Checked exception | Subclasse de `Exception`, mas **não** de `RuntimeException` | **Sim** | Sim |
| Error | Subclasse de `Error` | Não | Não (não deves) |

### Regra Handle or Declare
Para **checked exceptions**, o compilador obriga a escolher:
- **Handle** — tratar com `try/catch`
- **Declare** — declarar no método com `throws`

Para **unchecked** (runtime + errors) — opcional.

---

## 2. RuntimeException Classes

| Classe | Quem lança | Quando |
|---|---|---|
| `ArithmeticException` | JVM | Divisão de `int` por zero |
| `ArrayIndexOutOfBoundsException` | JVM | Índice inválido num array |
| `ClassCastException` | JVM | Cast inválido em runtime |
| `NullPointerException` | JVM | Chamada de método em referência `null` |
| `IllegalArgumentException` | Programador | Argumento inválido passado a um método |
| `NumberFormatException` | Programador | String não conversível para número |

### ⚠️ Ponto crítico
`NumberFormatException` **é subclasse de** `IllegalArgumentException`.

### Nota sobre divisão por zero
- `int / 0` → lança `ArithmeticException`
- `double / 0` → devolve `Infinity` (sem exceção)

---

## 3. Checked Exceptions & Error Classes

### Checked Exceptions

| Classe | Situação |
|---|---|
| `IOException` | Problema a ler/escrever ficheiro |
| `FileNotFoundException` | Ficheiro não existe |

> `FileNotFoundException` **é subclasse de** `IOException`

### Error Classes

| Classe | Quando ocorre |
|---|---|
| `ExceptionInInitializerError` | Um `static` initializer lança uma exceção |
| `StackOverflowError` | Recursão infinita — stack esgotada |
| `NoClassDefFoundError` | Classe disponível em compile-time mas não em runtime |

### Hierarquia completa para o exame

```
Throwable
├── Exception
│     ├── IOException                    (checked)
│     │     └── FileNotFoundException   (checked)
│     └── RuntimeException              (unchecked)
│           ├── ArithmeticException
│           ├── ArrayIndexOutOfBoundsException
│           ├── ClassCastException
│           ├── NullPointerException
│           └── IllegalArgumentException
│                 └── NumberFormatException
└── Error                               (unchecked — nunca capturar)
      ├── ExceptionInInitializerError
      ├── StackOverflowError
      └── NoClassDefFoundError
```

---

## 4. throw vs throws

| Keyword | Onde aparece | Para quê |
|---|---|---|
| `throw` | Dentro do corpo de um método | **Lançar** uma exceção naquele momento |
| `throws` | Na assinatura do método | **Declarar** que o método pode lançar uma exceção |

### Sintaxe correta

```java
// throw — dentro do método
throw new RuntimeException("mensagem");

// throws — na assinatura
void metodo() throws IOException { }
```

### Armadilhas do exame

```java
throw RuntimeException();          // DOES NOT COMPILE — falta new
throw new RuntimeException();      // correto

// Código inacessível após throw
try {
    throw new RuntimeException();
    throw new ArithmeticException(); // DOES NOT COMPILE — unreachable
} catch (Exception e) { }

// finally antes de catch
try { } finally { } catch (Exception e) { } // DOES NOT COMPILE

// try sozinho
try { fall(); }                    // DOES NOT COMPILE
```

---

## 5. try/catch — Sintaxe e Fluxo

### Anatomia

```java
try {
    // código protegido — pode lançar exceção
} catch (TipoExcecao e) {
    // handler — só corre se a exceção for lançada
}
```

### Regras obrigatórias
- Chavetas `{}` **sempre obrigatórias** (ao contrário de `if` e loops)
- O `try` precisa de pelo menos um `catch` **ou** um `finally`
- `try` sozinho não compila

### Fluxo de execução

```
try {
    A           ← executa
    EXCEÇÃO     ← lançada aqui
    B           ← NUNCA executa
} catch (...) {
    C           ← executa
}
D               ← executa (programa continua)
```

### Regra do catch
```
catch (TipoX e) apanha:
  ✅  TipoX exactamente
  ✅  Qualquer SUBCLASSE de TipoX
  ❌  Superclasse de TipoX
  ❌  Classe noutro ramo da hierarquia
```

---

## 6. Chaining catch Blocks

### Regra de ouro — ordem importa!

Java percorre os `catch` de cima para baixo e executa **apenas o primeiro que fizer match**.

| Ordem | Compila? | Porquê |
|---|---|---|
| Subclasse → Superclasse | ✅ | O mais específico primeiro |
| Superclasse → Subclasse | ❌ | Subclasse nunca seria alcançada (unreachable) |
| Classes sem relação hierárquica | ✅ | Qualquer ordem é válida |

### Exemplo

```java
// CORRETO — subclasse antes de superclasse
try {
    Integer.parseInt("abc");
} catch (NumberFormatException e) {    // subclasse
    System.out.println("NumberFormat");
} catch (IllegalArgumentException e) { // superclasse
    System.out.println("IllegalArgument");
}

// DOES NOT COMPILE — superclasse antes de subclasse
try {
    Integer.parseInt("abc");
} catch (IllegalArgumentException e) { // superclasse primeiro
    System.out.println("IllegalArgument");
} catch (NumberFormatException e) {    // DOES NOT COMPILE
    System.out.println("NumberFormat");
}
```

### Regras adicionais
- No máximo **um** catch executa por `try`
- A variável `e` de um catch não existe nos outros catches (scope próprio)
- `FileNotFoundException` deve aparecer antes de `IOException`
- `NumberFormatException` deve aparecer antes de `IllegalArgumentException`

---

## 7. Multi-catch Block

### Sintaxe

```java
} catch (ExcecaoA | ExcecaoB | ExcecaoC e) {
    // uma só variável no final
}
```

### Regras

| Regra | Válido? |
|---|---|
| Uma só variável no final | ✅ |
| Variável no meio ou repetida | ❌ |
| Subclasse e superclasse no mesmo multi-catch | ❌ (redundante) |
| Exceções sem relação hierárquica | ✅ |

### Armadilhas do exame

```java
// DOES NOT COMPILE — variável repetida
catch (ExcecaoA e | ExcecaoB e)

// DOES NOT COMPILE — variáveis diferentes
catch (ExcecaoA e1 | ExcecaoB e2)

// COMPILA — uma só variável no final
catch (ExcecaoA | ExcecaoB e)

// DOES NOT COMPILE — subclasse + superclasse (redundante)
catch (FileNotFoundException | IOException e)

// COMPILA — fica só a superclasse
catch (IOException e)
```

---

## 8. Bloco finally

### Estrutura

```java
try {
    // código protegido
} catch (Exception e) {  // opcional se finally existir
    // handler
} finally {
    // SEMPRE executa — com ou sem exceção
}
```

### Configurações válidas

| Configuração | Compila? |
|---|---|
| `try` + `catch` | ✅ |
| `try` + `finally` | ✅ |
| `try` + `catch` + `finally` | ✅ |
| `try` + `finally` + `catch` | ❌ ordem errada |
| `try` sozinho | ❌ |
| Dois `finally` | ❌ |

### Fluxo de execução

```
Sem exceção:          try → finally → continua
Com exceção:          try → catch → finally → continua
Exceção no finally:   finally → propaga (exceção do catch é PERDIDA!)
return no try/catch:  finally executa ANTES do return
return no finally:    finally VENCE — return do try/catch é ignorado
```

### ⚠️ System.exit()
A **única exceção** à regra "finally sempre executa":
```java
try {
    System.exit(0);         // JVM termina aqui
} finally {
    System.out.println("Nunca imprime"); // NÃO executa
}
```

---

## 9. Try-with-Resources

### Sintaxe

```java
try (Tipo recurso = new Tipo()) {
    // usa o recurso
} catch (Exception e) {   // opcional
    // handler
} finally {               // opcional
    // sempre executa
}
```

### Regras fundamentais

| Regra | Detalhe |
|---|---|
| `catch` e `finally` | Ambos **opcionais** |
| `AutoCloseable` | O recurso **tem de** implementar `AutoCloseable` |
| Múltiplos recursos | Separados por `;` — cada um com o seu tipo |
| Ordem de fecho | **Inversa** à ordem de declaração |
| Scope do recurso | Só dentro do bloco `try` |
| `finally` implícito | Executa **antes** de qualquer `catch`/`finally` do programador |

### Ordem de execução

```
1. Código do try
2. close() dos recursos (ordem INVERSA)   ← finally implícito
3. catch (se houve exceção)
4. finally do programador
```

### Armadilhas do exame

```java
// DOES NOT COMPILE — vírgula em vez de ponto e vírgula
try (MyClass a = new MyClass(1), MyClass b = new MyClass(2)) { }

// DOES NOT COMPILE — sem tipo na segunda declaração
try (MyClass a = new MyClass(1), b = new MyClass(2)) { }

// COMPILA — separados por ponto e vírgula com tipo
try (MyClass a = new MyClass(1); MyClass b = new MyClass(2)) { }

// COMPILA — var é permitido
try (var f = new FileInputStream("file.txt")) { }

// DOES NOT COMPILE — recurso fora do scope
try (Scanner s = new Scanner(System.in)) {
} catch (Exception e) {
    s.nextLine(); // DOES NOT COMPILE — s fora do scope
}
```

### Try tradicional vs Try-with-resources

| | Try tradicional | Try-with-resources |
|---|---|---|
| `catch` obrigatório? | Não (se tiver `finally`) | Não |
| `finally` obrigatório? | Não (se tiver `catch`) | Não |
| Fecha recursos automaticamente? | ❌ | ✅ |
| Scope do recurso | Fora do `try` | Só dentro do `try` |

---

## 10. Calling Methods + Override com Exceptions

### Chamar métodos com checked exceptions

```java
void eatCarrot() throws CheckedException { }

// DOES NOT COMPILE — checked exception ignorada
void bad() { eatCarrot(); }

// COMPILA — opção 1: tratar
void good1() {
    try { eatCarrot(); } catch (CheckedException e) { }
}

// COMPILA — opção 2: declarar
void good2() throws CheckedException { eatCarrot(); }
```

### ⚠️ Catch de checked exception impossível

```java
void eatCarrot() { } // não declara nada

void bad() {
    try { eatCarrot(); }
    catch (CheckedException e) { } // DOES NOT COMPILE — impossível de lançar
}
```

### Regras de Override com exceções

| O override pode... | Válido? |
|---|---|
| Declarar **menos** exceções que o pai | ✅ |
| Declarar **nenhuma** exceção | ✅ |
| Declarar uma **subclasse** da exceção do pai | ✅ |
| Declarar uma **nova** checked exception | ❌ |
| Declarar uma **superclasse** da exceção do pai | ❌ |
| Declarar nova **unchecked** exception | ✅ |

### Regra de ouro

```
Override de checked exceptions:
  O filho pode AFUNILAR (menos, subclasse, nenhuma)   ✅
  O filho NÃO pode ALARGAR (mais, superclasse, nova)  ❌
  Unchecked → sempre livre                            ✅
```

---

## 11. Printing an Exception

### As 3 formas

| Método | O que imprime | Exemplo |
|---|---|---|
| `System.out.println(e)` | Tipo + mensagem | `java.lang.RuntimeException: cannot hop` |
| `e.getMessage()` | Só a mensagem | `cannot hop` |
| `e.printStackTrace()` | Tipo + mensagem + hierarquia de chamadas | Stack trace completa |

### Exemplo de stack trace

```
java.lang.RuntimeException: cannot hop
    at Handling.hop(Handling.java:15)    ← onde foi lançada
    at Handling.main(Handling.java:7)    ← quem chamou hop()
```

### Notas
- `getMessage()` devolve `null` se a exceção foi criada sem mensagem
- `printStackTrace()` é o mais útil para debug — mostra toda a cadeia de chamadas
- No exame, a forma mais comum é `System.out.println(e)` (tipo + mensagem)

---

## Exam Essentials — Resumo Final

| Tópico | O que saber |
|---|---|
| Hierarquia | `Throwable` → `Exception`/`Error` → `RuntimeException` |
| Checked vs Unchecked | Checked obriga handle-or-declare; unchecked não |
| `throw` vs `throws` | `throw` lança; `throws` declara na assinatura |
| Ordem dos `catch` | Subclasse **sempre** antes de superclasse |
| Multi-catch | Uma variável no final; sem subclasse + superclasse juntas |
| `finally` | Sempre executa, exceto com `System.exit()` |
| Try-with-resources | Fecha na ordem inversa; implícito `finally` antes do programador |
| Override | Filho pode afunilar; nunca alargar checked exceptions |
| `NumberFormatException` | Subclasse de `IllegalArgumentException` |
| `FileNotFoundException` | Subclasse de `IOException` |

---

*Resumo gerado durante sessão de estudo OCP Java SE 11 — Cap. 10*
