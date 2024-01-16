package chapter_5.trywithresources.supress_exception;

public class JammedTurkeyCage_v2 implements AutoCloseable{

    @Override
    public void close() throws IllegalArgumentException {
        throw new IllegalArgumentException("Cage door does not close");
    }

    public static void main(String[] args) {
        try(JammedTurkeyCage_v2 t = new JammedTurkeyCage_v2()){
            throw new IllegalStateException("Turkeys ran off");
        }catch (IllegalArgumentException e){
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t: e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        }
    }
}
