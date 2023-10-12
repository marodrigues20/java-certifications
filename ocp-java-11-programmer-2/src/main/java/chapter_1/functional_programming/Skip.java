package chapter_1.functional_programming;

/**
 * It is not valid functinal interface because it has two abstract methods: the inherited sprint() method and the
 * declared skip method.
 */
public interface Skip extends Sprint{

    void skip();
}
