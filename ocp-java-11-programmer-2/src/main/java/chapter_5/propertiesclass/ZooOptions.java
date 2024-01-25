package chapter_5.propertiesclass;

import java.util.Properties;

public class ZooOptions {

    public static void main(String[] args) {

        var props = new Properties();
        props.setProperty("name", "Our zoo");
        props.setProperty("open", "10am");
    }
}
