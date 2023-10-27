package chapter_2.target_annotation;

/**
 * Line 7 compiles, because it is permitted on a method declaration.
 * Line 10 does not compile, because it is not permitted on a cast operation.
 * Line 13 compiles, because it is applied to the constructor declaration.
 * Line 15 does not compile because the annotation is not marked for use in a constructor parameter.
 * Line 20 does not compile because it cannot be applied to fields or variables.
 */
public class Events {
    @ZooAttraction
    String rideTrain(){
        //return (@ZooAttraction String) "Fun!";
        return "Fun!";
    }
//    @ZooAttraction
//    Events(@ZooAttraction String description){
//        super();
//    }

    //@ZooAttraction int numPassengers;
}
