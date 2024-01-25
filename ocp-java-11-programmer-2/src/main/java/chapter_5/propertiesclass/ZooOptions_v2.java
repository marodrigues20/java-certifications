package chapter_5.propertiesclass;

import java.util.Properties;

public class ZooOptions_v2 {

    public static void main(String[] args) {

        var props = new Properties();
        props.put("tigerAge", "4");
        props.put("lionAge", 5);
        System.out.println(props.getProperty("tigerAge"));
        System.out.println(props.getProperty("lionAge"));

    }
}
