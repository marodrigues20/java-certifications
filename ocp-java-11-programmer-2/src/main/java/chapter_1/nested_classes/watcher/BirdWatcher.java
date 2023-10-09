package chapter_1.nested_classes.watcher;


import chapter_1.nested_classes.bird.Toucan;

/**
 * Importing a static nested class is interesting. You can import it using a regular import.
 * Since it is static, you can also use a static import.
 * import static chapter_1.nested_classes.bird.Toucan.Beak;
 *
 * Either one will compile. Surprising, isn't it? Remember, Java treats the enclosing class as if were a namespace.
 */
public class BirdWatcher {
    Toucan.Beak beak;
}
