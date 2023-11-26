package chapter_3.sort.comparable;

public class Product implements Comparable<Product>{

    private int id;
    private String name;

    public int hashCode() {
        return id;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Product)) return false;
        var other = (Product) obj;
        return this.id == other.id;
    }
    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }
}
