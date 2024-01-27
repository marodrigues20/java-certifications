package chapter_5.reviewed_questions.question_27;

import java.text.NumberFormat;
import java.util.Locale;


/**
 * 27. What is the output of the following program?
 *
 * Answer is E.
 *
 * The Locale.Builder class requires that the build() method be called to actually create the Locale object.
 * For this reason, the two Locale.setDefault() statements do not compile because the input is not a Locale, making
 * option E the correct answer.
 * If the proper build() calls were added, then the code would compile and print the value for Germany, 2,40 €.
 * As in the exam, though, you did not have to know the format of currency values in a particular locale to answer
 * the question. Note that the default locale category is ignored since an explicit currency locale is selected.
 */
public class Wallet {

    private double money;

    public Wallet(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private String openWallet(){
        Locale.setDefault(Locale.Category.DISPLAY,
                new Locale.Builder().setRegion("us").build());

        Locale.setDefault(Locale.Category.FORMAT,
                new Locale.Builder().setLanguage("en").build());

        return NumberFormat.getCurrencyInstance(Locale.GERMANY).format(money);
    }

    public void printBalance(){
        System.out.println(openWallet()); //2,40 €
    }

    public static void main2(String... unused){
        new Wallet(2.4).printBalance();
    }

    public static void main(String[] args) {
        main2();
    }
}
