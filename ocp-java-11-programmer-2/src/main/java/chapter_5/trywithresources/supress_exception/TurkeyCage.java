package chapter_5.trywithresources.supress_exception;

public class TurkeyCage implements AutoCloseable{
    @Override
    public void close() throws Exception {
        System.out.println("Close gate");
    }

    public static void main(String[] args) throws Exception {
        try(var t = new TurkeyCage()) {
            System.out.println("Put turkeys in");
        }
    }
}
