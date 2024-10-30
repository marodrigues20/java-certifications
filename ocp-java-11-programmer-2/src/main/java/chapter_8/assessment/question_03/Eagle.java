package chapter_8.assessment.question_03;

import java.io.Serializable;

public class Eagle extends Bird implements Serializable {
    {this.name = "Olivia";}
    public Eagle(){
        this.name = "Bridget";
    }


    public static void main(String[] args){
        var e = new Eagle();
        e.name = "Adeline";
    }
}
