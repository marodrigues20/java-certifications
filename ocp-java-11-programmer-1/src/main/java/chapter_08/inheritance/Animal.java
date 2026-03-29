package chapter_08.inheritance;


/**
 * Regras fundamentais:
 * Java só tem herança simples — uma classe só pode ter um extends
 * Toda classe que não declara extends herda implicitamente de Object
 * O filho herda tudo que é public ou protected do pai
 * O que é private no pai não é acessível diretamente no filho (existe, mas escondido)
 */
public class Animal {

    protected String nome;

    public void eat() {
        System.out.println("Animal comendo");
    }
}