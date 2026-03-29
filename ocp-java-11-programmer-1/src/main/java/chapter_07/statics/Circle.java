package chapter_07.statics;

import static java.lang.Math.sqrt;
import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.System.out;

public class Circle {
    public static void main(String[] args) {
        double radius = 5.0;

        // sem static import seria:
        // double area = Math.PI * Math.pow(radius, 2);
        // System.out.println("Area: " + area);

        // com static import fica:
        double area = PI * pow(radius, 2);
        out.println("Area: " + area);  // 78.53...

        // importando tudo de Math de uma vez
        // import static java.lang.Math.*;
        double diagonal = sqrt(pow(3, 2) + pow(4, 2));
        out.println("Diagonal: " + diagonal);  // 5.0
    }
}

