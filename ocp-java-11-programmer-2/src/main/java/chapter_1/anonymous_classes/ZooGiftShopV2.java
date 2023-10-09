package chapter_1.anonymous_classes;

/**
 * This example is exactly example from ZooGiftShop class. However, here we are using an interface instead of an
 * abstract class. Line 15 is public instead of using default access since interfaces required public methods.
 * The anonymous class is the same whether you implement an interface or extend a class!
 */
public class ZooGiftShopV2 {
    interface SaleTodayOnlyV2 {
        int dollarsOff();
    }

    public int admission(int basePrice) {
        SaleTodayOnlyV2 sale = new SaleTodayOnlyV2() {
            @Override
            public int dollarsOff() {
                return 3;
            }
        };
        return basePrice - sale.dollarsOff();
    }

}

