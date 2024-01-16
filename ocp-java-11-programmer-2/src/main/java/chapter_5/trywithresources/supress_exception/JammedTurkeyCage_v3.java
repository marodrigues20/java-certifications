package chapter_5.trywithresources.supress_exception;

public class JammedTurkeyCage_v3 implements AutoCloseable{

    @Override
    public void close() throws IllegalArgumentException {
        throw new IllegalArgumentException("Cage door does not close");
    }

    public static void main(String[] args) {
        try(JammedTurkeyCage_v3 t = new JammedTurkeyCage_v3()){
            throw new RuntimeException("Turkeys ran off");
        }catch (IllegalArgumentException e){
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
