package chapter_2.creating_annotation.stage_03;

import chapter_2.creating_annotation.stage_02.Exercise;

public @interface Panda {
    //Integer height(); // primitive types like int and long are supported, wrapper classes like Integer and Long are not.
    //String[][] generalInfo(); // The type String[] is supported, as it is an array of String values, but String[][] is not.
    Size size() default Size.SMALL; // Compile because it is an Enum
    //Bear friendlyBear(); // The type of friendlyBear() is Bear (not Class). Even if Bear were changed to an interface,
                         // the friendlyBear() element would still not compile since it is not one supported types.
    Exercise exercise() default @Exercise(hoursPerDay = 2); //Compile because it is an annotation
}
