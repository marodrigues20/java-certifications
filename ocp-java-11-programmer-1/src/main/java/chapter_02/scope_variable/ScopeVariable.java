package chapter_02.scope_variable;


/**
 * | Tipo | Morre quando... |
 * |---|---|
 * | **Local** | Fecha o bloco `}` |
 * | **Instância** | Objeto é coletado pelo GC |
 * | **Classe (`static`)** | Programa termina |
 */
public class ScopeVariable {


    public void eatIfHungry(boolean hungry) {

        if (hungry) {
            int bitesOfCheese = 1;
        }  // bitesOfCheese morre aqui

        //System.out.println(bitesOfCheese);  // ❌ NÃO COMPILA
    }


    public void eatIfHungry2(boolean hungry){

        if (hungry) {
            int cheese = 1;
            {
                System.out.println(cheese);  // ✅ interno enxerga externo
                var tiny = true;
            }
            //System.out.println(tiny);        // ❌ externo não enxerga interno - NÃO COMPILA
        }

    }
}
