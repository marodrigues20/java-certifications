package chapter_02.indentifiers;


/**
 * 4 regras para nomes válidos em Java:
 *
 * Pode começar com letra, $ ou _
 * Pode conter números, mas não pode começar com número
 * _ sozinho não é permitido (desde Java 9)
 * Não pode ser uma palavra reservada (class, int, for...)
 */
public class Identifiers {

    long okIdentifier;      // ✅
    float $OK2;             // ✅
    boolean _alsoOK;        // ✅

    //int 3DMap;              // ❌ começa com número
    //byte hollywood@vine;    // ❌ @ não é permitido
    //double public;          // ❌ palavra reservada
    //short _;                // ❌ underscore sozinho

}
