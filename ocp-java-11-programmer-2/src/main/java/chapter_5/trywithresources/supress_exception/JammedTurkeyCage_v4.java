package chapter_5.trywithresources.supress_exception;

import chapter_1.interface_members.inheritance_duplicate.Run;

public class JammedTurkeyCage_v4 implements AutoCloseable{
    @Override
    public void close() throws Exception {

    }

    public static void main(String[] args) {
        try(JammedTurkeyCage_v4 t = new JammedTurkeyCage_v4()){
            throw new IllegalStateException("Turkeys run off");
        }finally {
            throw new RuntimeException("and we couldn't find them");
        }
    }
}
