package chapter_1.enum_modifier;


/**
 * This technique of a constructor and state allows you to combine logic with benefit of a list of values. Sometimes,
 * you want to do more. For example, our zoo has different seasonal hours. It is cold and gets dark early in the winter.
 * We could keep track of the hours through instance variables, or we can let each enum value manage hours itself.
 *
 * If we forget to implement the method for one of the values, then we get a compile error.
 */
public enum SeasonV3 {

    WINTER {
        public String getHours(){
            return "10am-3pm";
        }
    },
    SPRING {
        public String getHours() {
            return "9am-5pm";
        }
    },
    SUMMER{
        public String getHours(){
            return "9am-7am";
        }
    },
    FALL{
        public String getHours(){
            return "9am-5pm";
        }
    };

    public abstract String getHours();

}
