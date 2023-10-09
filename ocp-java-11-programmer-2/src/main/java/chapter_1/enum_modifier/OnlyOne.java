package chapter_1.enum_modifier;

import org.w3c.dom.ls.LSOutput;

public enum OnlyOne {
    ONCE(true);
    private OnlyOne(boolean b){
        System.out.printf("construction");
    }



}
