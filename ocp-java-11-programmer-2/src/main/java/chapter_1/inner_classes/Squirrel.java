package chapter_1.inner_classes;

/**
 * Does not compile for two reasons:
 * 1. Even though it is an instance method, it is not an instance method inside the Fox class.
 * 2. Adding a Fox reference would not fix the problem entirely, though. Den is private and not accessible in the
 * Squirrel class.
 */
public class Squirrel {
    public void visitFox() {
        //new Den(); // DOES NOT COMPILE
    }
}
