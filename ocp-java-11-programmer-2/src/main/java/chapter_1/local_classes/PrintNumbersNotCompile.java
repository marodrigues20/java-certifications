package chapter_1.local_classes;


/**
 * The length and height variables are final and effectively final, respectively, so neither causes a compilation issue.
 * One the other hand, the width variable is reassigned during the method so it cannot be effectively final. For this reason,
 * the local class declaration does not compile.
 */
public class PrintNumbersNotCompile {

    public void processData(){
        final int lenght = 5;
        int width = 10;
        int height = 2;

        class VolumeCalculator{
            public int multiply(){
                return lenght * width * height; //DOES NOT COMPILE
            }
        }
        //width = 2;
    }
}
