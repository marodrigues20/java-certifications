package chapter_03.pattern_matchin_21;


/**
 * O switch clássico só compara valores — 1, 2, 3...
 */
public class SwitchClassico {


    public static void main(String[] args) {

        int dia = 2;

        switch (dia) {
            case 1:
                System.out.println("Segunda");
                break;
            case 2:
                System.out.println("Terça");   // ← entra aqui
                break;
            default:
                System.out.println("Outro dia");
        }
    }
}

