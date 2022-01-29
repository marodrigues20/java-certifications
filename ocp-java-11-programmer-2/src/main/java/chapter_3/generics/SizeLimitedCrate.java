package chapter_3.generics;


/**
 * Generic classes are not limited to having a single type parameter. This class shows two generic parameters.
 * @param <T> - represents the type that we are putting in the crate.
 * @param <U> - represents the unit that we are using to measure the maximum size for the crate.
 */
public class SizeLimitedCrate<T, U> {

    private T contents;
    private U sizeLimit;

    public SizeLimitedCrate(T contents, U sizeLimit) {
        this.contents = contents;
        this.sizeLimit = sizeLimit;
    }
}
