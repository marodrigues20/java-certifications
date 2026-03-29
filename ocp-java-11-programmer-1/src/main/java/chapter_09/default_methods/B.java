package chapter_09.default_methods;

interface B {

    default void hello() {
        System.out.println("B");
    }

}
