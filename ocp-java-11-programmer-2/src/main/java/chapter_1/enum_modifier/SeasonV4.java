package chapter_1.enum_modifier;


/**
 * If we don't want each and every enum value to have a method, we can create a default implementation and override
 * it only for the special cases.
 * We only code the special cases and let the others use the enum provided implementation. Of course, overriding getHours()
 * is possible only if it is not marked final.
 * Just because an enum can have lots of methods doesn't mean that it should.
 */
public enum SeasonV4 {

    WINTER {
        public String getHours(){
            return "10am-3pm";
        }
    },
    SUMMER {
        public String getHours(){
            return "9am-7pm";
        }
    },
    SPRING, FALL;

    public String getHours(){
        return "9am-5pm";
    }
}
