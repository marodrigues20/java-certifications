package chapter_2.applying_annotation;

public @interface Swimmer {
    int armLength = 10;
    String stroke();
    String name();
    String favoriteStroke() default "Backstroke";
}
