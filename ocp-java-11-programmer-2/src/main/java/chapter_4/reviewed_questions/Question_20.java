package chapter_4.reviewed_questions;


/**
 * Which of the following functional interfaces contain an abstract method that returns a primitive value?
 * (Choose all that apply.)
 * Answer A,C,E.
 *
 * Java includes support for three primitives streams, along with numerous functional interfaces to go with them:
 * int, double, and long.
 * For this reason, option C and E are correct.
 * There is one exception to this rule.
 * While there is no BooleanStream class, there is a BooleanSupplier functional interface, making option A correct.
 * Java does not include primitive streams or related functional interfaces for other numeric data types, making
 * option B and D incorrect. Option F is incorrect because String is not a primitive, but an object.
 * Only primitives have custom suppliers.
 */
public class Question_20 {

    //A. BooleanSupplier
    //B. CharSupplier
    //C. DoubleSupplier
    //D. FloatSupplier
    //E. IntSupplier
    //F. StringSupplier

}
