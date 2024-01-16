package chapter_5.trywithresources;

public class MultipleResourcesUse {

    public static void main(String[] args) throws Exception {

        try ( var bookReader = new MyFileReader("1");
              var movieReader = new MyFileReader("2");
              var tvReader = new MyFileReader("3");){
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }

    }
}
