package chapter_03.operators;


/**
 * | Situação          | Nome      | Resultado                |
 * |-------------------|-----------|--------------------------|
 * | Passa do máximo   | Overflow  | Salta para o mínimo      |
 * | Passa do mínimo   | Underflow | Salta para o máximo      |
 */
public class IntOverflow {
    public static void main(String[] args) {

        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        System.out.println("MAX_INT        = " + max);        //  2147483647
        System.out.println("MAX_INT + 1    = " + (max + 1));  // -2147483648 (overflow!)
        System.out.println("MIN_INT        = " + min);        // -2147483648
        System.out.println("MIN_INT - 1    = " + (min - 1));  //  2147483647 (underflow!)

        // Solução: usar long em vez de int
        long semOverflow = (long) max + 1;
        System.out.println("Com long       = " + semOverflow); // 2147483648 ✅
    }
}
