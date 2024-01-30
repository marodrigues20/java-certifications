package Appendix_A.type_reference;

public class VarKeyword {

    //var tricky = "Hello!";  // DOES NOT COMPILE

    public void whatTypeAmI(){
        var name = "Hello";
        var size = 7;
    }

    public void reassignment(){
        var number = 7;
        number = 4;
        //number = "five"; // DOES NOT COMPILE. INCOMPATIBLE TYPE FOUND
    }

    public void promotedType(){
        var apples = (short)10;
        apples = (byte)5;
        //apples = 1_000_000; // DOES NOT COMPILE
    }

    public void doesThisCompile(boolean check){
        //var question;  // DOES NOT COMPILE
        //question = 1;
        //var answer;   // DOES NOT COMPILE
        if(check){
            //answer = 2;
        }else{
            //answer = 3;
        }
        //System.out.println(answer);
    }

    public void twoTypes(){
        //int a, var b = 3;  // DOES NOT COMPILE
        //var n = null;      // DOES NOT COMPILE
    }

}
