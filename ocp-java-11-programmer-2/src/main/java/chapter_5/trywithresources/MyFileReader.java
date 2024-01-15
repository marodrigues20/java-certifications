package chapter_5.trywithresources;

public class MyFileReader implements AutoCloseable{

    private String tag;

    public MyFileReader(String tag){
        this.tag = tag;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closed:" + tag);
    }
}
