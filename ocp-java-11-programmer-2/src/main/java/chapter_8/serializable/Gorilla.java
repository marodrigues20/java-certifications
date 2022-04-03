package chapter_8.serializable;

import java.io.Serializable;

/**
 * In this example, the Gorilla class contains three instance members (name, age, friendly) that will be saved to a
 * stream if the class is serialized.
 *
 * What about the favoriteFood field that is market transient? Any field that is marked transient will not be saved
 * to a stream when the class is serialized.
 */
public class Gorilla implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Boolean friendly;
    private transient String favoriteFood;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getFriendly() {
        return friendly;
    }

    public void setFriendly(Boolean friendly) {
        this.friendly = friendly;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }
}
