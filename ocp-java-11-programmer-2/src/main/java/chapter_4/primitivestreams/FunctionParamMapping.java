package chapter_4.primitivestreams;


/**
 * Function parameters when mapping between types of streams
 *
 *  Source stream class | To create Stream   | To create DoubleStream | To Create IntStream  | To Create LongStream
 *  Stream<T>           | Function<T,R>      | ToDoubleFunction<T>    | ToIntFunction<T>     | ToLongFunction<T>
 *  DoubleStream        | Double Function<R> | DoubleUnary Operator   | DoubleToInt Function | DoubleToLong Function
 *  IntStream           | IntFunction<R>     | IntToDoubleFunction    | IntUnary Operator    | IntToLong Function
 *  LongStream          | LongFunction<R>    | LongToDouble Function  | LongToInt Function    | LongUnary Operator
 */
public class FunctionParamMapping {

    public static void main(String[] args) {


    }
}
