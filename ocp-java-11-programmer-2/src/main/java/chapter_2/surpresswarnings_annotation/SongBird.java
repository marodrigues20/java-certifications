package chapter_2.surpresswarnings_annotation;

import java.util.List;

public class SongBird {

    @Deprecated
    static void sing(int volume){}

    static Object chirp(List<String> data){
        return data.size();
    }
}
