package chapter_3.generics;

/**
 * When you instantiate the class you should tell the compiler what T should be for that particular instance.
 * The convention
 * E for an Element
 * K for a map Key
 * V for a map value
 * N for a number
 * T for a generic data type
 * S, U, V, and so forth for multiple generic type
 *
 * Create class not needing to know about the objets that go into it, those objects don't need to know about Crate
 * either. We arent' requiring the objects to implement an interface named Creatable or like that. A class can be
 * put in the Crate without any changes at all.
 * @param <T>
 */
public class Crate<T> {

    private T contents;

    public T emptyCreate(){
        return contents;
    }

    public void packCrate(T contents){
        this.contents = contents;
    }

    //Tip: The return will be the same type specified in generic class. However the T parameter of the method
    //can be another one. ie: String
    public <T> T tricky(T t){
        return t;
    }
}
