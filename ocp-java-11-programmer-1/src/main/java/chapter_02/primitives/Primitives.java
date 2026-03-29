package chapter_02.primitives;


/**
 * | Tipo | Tamanho | Sufixo obrigatório |
 * |------|---------|-------------------|
 * | `boolean` | true/false | — |
 * | `byte` | 8 bits | — |
 * | `short` | 16 bits | — |
 * | `int` | 32 bits | — |
 * | `long` | 64 bits | `L` |
 * | `float` | 32 bits decimal | `f` |
 * | `double` | 64 bits decimal | — |
 * | `char` | 16 bits Unicode | — |
 */
public class Primitives {

    long big   = 3123456789L;  // sem o L → NÃO COMPILA
    float price = 9.99f;       // sem o f → Java trata como double → NÃO COMPILA

}
