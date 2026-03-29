package chapter_02.constructor;


// Se não declarar nenhum, Java cria um construtor padrão vazio automaticamente.
public class Chick {
    public Chick() { }        // construtor ✅
    public void Chick() { }   // método normal, NÃO é construtor ❌
}
