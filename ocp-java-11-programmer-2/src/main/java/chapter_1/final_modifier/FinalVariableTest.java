package chapter_1.final_modifier;

/**
 * Final Modifier
 */
public class FinalVariableTest {

    public static void main(String[] args) {
        FinalVariableTest test = new FinalVariableTest();
        test.printZooInfo(true);
    }


    /**
     * As shown with the lemur variable, we don't need to assign a value when a final variable is declared.
     * The rule is only that it must be assigned a value before it can be used.
     */
    private void printZooInfo(boolean isWeekend){
        final int giraffe = 5;
        final long lemur;

        if(isWeekend) lemur = 5;
        else lemur = 10;
        System.out.println(giraffe + " " + lemur);
    }


    /**
     * This snippet contains two compilation errors. The giraffe is already assigned a value.
     * The second compilation error is from attempting to use the lemur variable, which would not be assigned a value
     * if isWeekend is false. The compiler does not allow the use of local variables that may not have been assigned a
     * value, whether they are marked final or not.
     */
    private void printZooInfo_2(boolean isWeekend){
        final int giraffe = 5;
        final long lemur;

        //if(isWeekend) lemur = 5;
        //else giraffe = 3;    //DOES NOT COMPILE
        //System.out.println(giraffe + " " + lemur); //DOES NOT COMPILE
    }


    /**
     * Just because a variable reference is marked final does not mean the object associated with it cannot be
     * modified.
     */
    private void printZooInfo_3(){
        final StringBuilder cobra = new StringBuilder();
        cobra.append("Hssss");
        cobra.append("Hsssss!!!");
    }
}
