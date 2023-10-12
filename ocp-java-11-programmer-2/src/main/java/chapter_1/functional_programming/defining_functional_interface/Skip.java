package chapter_1.functional_programming.defining_functional_interface;

/**
 * It is not valid functinal interface because it has two abstract methods: the inherited sprint() method and the
 * declared skip method.
 */
public interface Skip extends Sprint{

    void skip();
}
