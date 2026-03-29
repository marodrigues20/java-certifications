package chapter_02.multiple_variables;

public class MutlipleVariables {

    String s1, s2;                 // ✅ duas declaradas
    String s3 = "yes", s4 = "no"; // ✅ duas declaradas e inicializadas



    int i1, i2, i3 = 0;           // ✅ três declaradas, só i3 inicializada

    //int num, String value;         // ❌ tipos diferentes na mesma linha

        public void test() {
            int i1, i2, i3 = 0;     // variáveis locais
            //System.out.println(i1);  // ❌ NÃO COMPILA — local sem inicializar
        }


        public static void main(String[] args) {
        MutlipleVariables mutlipleVariables = new MutlipleVariables();
        mutlipleVariables.test();
    }


}
