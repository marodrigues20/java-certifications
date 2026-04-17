package chapter_10.bloco_10;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Override de checked exceptions:
 *   O filho pode AFUNILAR (menos, subclasse, nenhuma)
 *   O filho NAO pode ALARGAR (mais, superclasse, nova checked)
 *   Unchecked → sempre livre, pai ou filho podem declarar à vontade
 */
public class Bloco10CallingEOverride {

    // --- Excecoes custom para os exemplos ---
    static class CanNotHopException extends Exception { }

    // --- Parte 1: Chamar metodos que lancam excecoes ---

    // metodo que declara checked exception
    static void eatCarrot() throws CanNotHopException {
        // pode ou nao lancar — o compilador nao sabe
    }

    // opcao 1 — tratar com try/catch
    static void chamadorTrata() {
        System.out.println("--- Chamador trata ---");
        try {
            eatCarrot();
            System.out.println("sem excecao desta vez");
        } catch (CanNotHopException e) {
            System.out.println("tratou: " + e.getClass().getSimpleName());
        }
    }

    // opcao 2 — propagar com throws
    static void chamadorPropaga() throws CanNotHopException {
        System.out.println("--- Chamador propaga ---");
        eatCarrot(); // delega ao seu chamador
    }

    // --- Parte 2: Override com excecoes ---

    static class Hopper {
        // pai declara checked exception
        public void hop() throws CanNotHopException { }

        // pai declara IOException
        public void jump() throws IOException { }

        // pai nao declara nada
        public void skip() { }
    }

    static class BunnyValido extends Hopper {

        // valido — declara MENOS excecoes (nenhuma)
        @Override
        public void hop() { }

        // valido — declara SUBCLASSE de IOException
        @Override
        public void jump() throws FileNotFoundException { }

        // valido — unchecked exception sempre permitida
        @Override
        public void skip() throws IllegalArgumentException { }
    }

    /*

    Classe invalida — nao compila
    static class BunnyInvalido extends Hopper {

        // DOES NOT COMPILE — nova checked exception (pai nao declarava nada)
        public void skip() throws CanNotHopException { }

        // DOES NOT COMPILE — superclasse de CanNotHopException
        public void hop() throws Exception { }
    }
    */

    // --- Demonstracao do fluxo ---
    static void demonstracaoFluxo() {
        System.out.println("--- Demonstracao BunnyValido ---");
        BunnyValido b = new BunnyValido();

        // hop() — nao declara excecao, nao precisamos de tratar
        b.hop();
        System.out.println("hop() sem excecao");

        // jump() — declara FileNotFoundException (checked), temos de tratar
        try {
            b.jump();
            System.out.println("jump() sem excecao");
        } catch (IOException e) {
            System.out.println("jump() excecao: " + e.getMessage());
        }

        // skip() — unchecked, nao precisamos de tratar
        b.skip();
        System.out.println("skip() sem excecao");
    }

    public static void main(String[] args) {
        chamadorTrata();
        System.out.println();

        try {
            chamadorPropaga();
            System.out.println("chamadorPropaga sem excecao");
        } catch (CanNotHopException e) {
            System.out.println("main tratou: " + e.getClass().getSimpleName());
        }
        System.out.println();

        demonstracaoFluxo();
    }
}
