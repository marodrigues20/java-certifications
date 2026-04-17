package chapter_10.bloco_09;


public class Bloco09TryWithResources {

    // Classe auxiliar que implementa AutoCloseable
    static class MyResource implements AutoCloseable {
        private final int num;

        MyResource(int num) {
            this.num = num;
            System.out.println("Abrindo recurso: " + num);
        }

        @Override
        public void close() {
            System.out.println("Fechando recurso: " + num);
        }
    }

    // 1. Try-with-resources basico — close() automatico
    static void exemploBasico() {
        System.out.println("--- Basico ---");
        try (MyResource r = new MyResource(1)) {
            System.out.println("usando recurso: " + r.num);
        } // close() chamado aqui automaticamente
        System.out.println("apos try");
    }

    // 2. Multiplos recursos — fecho na ordem INVERSA
    static void exemploMultiplosRecursos() {
        System.out.println("--- Multiplos recursos ---");
        try (MyResource r1 = new MyResource(1);
             MyResource r2 = new MyResource(2)) {
            System.out.println("usando r1 e r2");
            throw new RuntimeException("erro no try");
        } catch (RuntimeException e) {
            System.out.println("catch: " + e.getMessage());
        } finally {
            System.out.println("finally do programador");
        }
        // Ordem: fecha r2, fecha r1, catch, finally
    }

    // 3. Finally implicito executa ANTES do finally do programador
    static void exemploOrdemFinally() {
        System.out.println("--- Ordem do finally ---");
        try (MyResource r = new MyResource(10)) {
            System.out.println("try executou");
        } finally {
            // close() do recurso ja executou antes de chegar aqui!
            System.out.println("finally do programador");
        }
    }

    // 4. Scope — recurso nao existe no catch nem finally
    static void exemploScope() {
        System.out.println("--- Scope ---");
        try (MyResource r = new MyResource(5)) {
            System.out.println("r existe aqui: " + r.num);
        } catch (Exception e) {
            // r NAO existe aqui — fora do scope
            System.out.println("r nao existe no catch");
        }
        // r NAO existe aqui tambem
    }

    // 5. Sem catch nem finally — valido em try-with-resources!
    static void exemploSemCatchFinally() {
        System.out.println("--- Sem catch nem finally ---");
        try (MyResource r = new MyResource(99)) {
            System.out.println("recurso usado");
        }
        // close() e chamado automaticamente — nao precisas de finally!
    }

    public static void main(String[] args) {
        exemploBasico();
        System.out.println();

        exemploMultiplosRecursos();
        System.out.println();

        exemploOrdemFinally();
        System.out.println();

        exemploScope();
        System.out.println();

        exemploSemCatchFinally();
    }
}
