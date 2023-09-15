package chapter_1.enum_modifier;

public class SwitchEnum {

    public static void main(String[] args) {
        Season summer = Season.SUMMER;

        switch (summer) {
            case WINTER:
                System.out.println("Get out the sled");
                break;
            case SUMMER:
                System.out.println("Time for the pool");
                break;
            default:
                System.out.println("Is it summer yet?");
        }

        /*switch (summer){ //compiler already knows that FALL is a type of summer enum.
            case summer.FALL:
                System.out.println("Rake some leaves!");
                break;
            case 0:         // DOES NOT COMPILE because is not one value of Enum
                System.out.println("Get out of sled");
                break;
        }*/
    }
}
