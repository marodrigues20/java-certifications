package chapter_5.trywithresources;

public class MyFileReaderFinalUse {

    public static void main(String[] args) throws Exception {
        MyFileReaderFinalUse myFileReaderFinalUse = new MyFileReaderFinalUse();
        myFileReaderFinalUse.relax();
    }

    public void relax() throws Exception {
        final var bookReader = new MyFileReader("4");
        MyFileReader movieReader = new MyFileReader("5");
        try (bookReader;
            var tvReader = new MyFileReader("6");
            movieReader){
            System.out.println("Try Block");
        }finally {
            System.out.println("Finally Block");
        }
    }
}
