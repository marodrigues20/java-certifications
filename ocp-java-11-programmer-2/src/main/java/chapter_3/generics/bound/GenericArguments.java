package chapter_3.generics.bound;

import java.util.List;

public class GenericArguments {

    class A{ }
    class B extends A { }
    class C extends A { }

    //Work perfectly
    <T> T first(List<? extends T> list){
        return list.get(0);
    }

    //DOES NOT COMPILE because the return isn't actually a type
    //<T> <? extends T> second(List<? extends T> list){ //DOES NOT COMPILE
    //    return list.get(0);
    //}

    //DOES NOT COMPILE because B is not a type parameter
    //<B extends A> B third(List<B> list){
    //    return new B(); //DOES NOT COMPILE
    //}

    void fourth(List<? super B> list){

    }

    //It tries to mix a method-specific type parameter with wildcard.
    //A wildcard must be a ? in it.
    //<X> void fifth(List<X super B> list){ } //DOES NOT COMPILE


}
