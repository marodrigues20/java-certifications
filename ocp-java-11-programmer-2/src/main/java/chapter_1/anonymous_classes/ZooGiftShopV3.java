package chapter_1.anonymous_classes;


/**
 * Line 14 through 19 are anonymous class. We don't even store it in a local variable. Instead, we pass it directly to the
 * method that needs it. Reading this style of code does take some getting used to. But it is a concise way to create a
 * class that you will use only once
 */
public class ZooGiftShopV3 {

    interface SaleTodayOnly {
        int dollarsOff();
    }

    public int pay() {
        return admission(5, new SaleTodayOnly() {
            @Override
            public int dollarsOff() {
                return 3;
            }
        });
    }

    public int admission(int basePrice, SaleTodayOnly sale){
        return basePrice - sale.dollarsOff();
    }
}
