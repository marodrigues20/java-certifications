package chapter_8.file;


import java.nio.charset.Charset;

/**
 * Java supports numerous character encoding, each specify by a different standard name value.
 */
public class CharacterEncodingExample {

    public static void main(String[] args) {

        Charset usAsciiCharset = Charset.forName("US-ASCII");
        Charset utf8Charset = Charset.forName("UTF-8");
        Charset utf16Charset = Charset.forName("UTF-16");

    }
}
