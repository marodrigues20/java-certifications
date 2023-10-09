package chapter_1.anonymous_classes;


/**
 * Anonymous classes are required to extend an existing class or implement an existing interface.
 * They are useful when you have a short implementation that will not be used anywhere else.
 */
public class ZooGiftShop {

    // Class body
    abstract class SaleTodayOnly{
        abstract int dollarsOff();
    }

    public int admission(int basePrice){

        /**
         *  Anonymous class.
         *  In this example, this equivalent to writing a local class with an unspecified name that extends SaleTodayOnly
         *  and immediately using it.
         *
         *  Pay attention on line 25. We are declaring in others words a local variable on these lines. Local variables
         *  declarations are required to end with semicolons.
         */

        SaleTodayOnly sale = new SaleTodayOnly() {
            @Override
            int dollarsOff() {
                return 3;
            }
        }; //Don't forget the semicolon!
        return basePrice - sale.dollarsOff();
    }

}
