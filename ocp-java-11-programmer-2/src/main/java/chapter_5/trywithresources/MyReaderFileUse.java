package chapter_5.trywithresources;

public class MyReaderFileUse {


    public static void main(String[] args) throws Exception {

        try (var bookReader = new MyFileReader("monkey")){
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }
    }
}
