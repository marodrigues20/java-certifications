package chapter_08.abstracts.example_01;


/**
 * Regras importantes:
 * Classe abstract pode ter métodos concretos (com corpo)
 * Classe abstract pode ter construtores — mas só chamados via super() pelo filho
 * Se o filho não implementar todos os métodos abstract, o filho também vira abstract
 * Método abstract só pode existir dentro de classe abstract
 */
public abstract class Shape {

    public abstract double calculateArea();

    public void display() {
        System.out.println("Area: " + calculateArea());
    }

}
