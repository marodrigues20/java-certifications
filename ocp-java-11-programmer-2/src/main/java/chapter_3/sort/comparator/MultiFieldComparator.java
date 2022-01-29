package chapter_3.sort.comparator;

import java.util.Comparator;

/**
 * Comparator are helpful objects. They let you separate sort order from the object to be sorted.
 */
public class MultiFieldComparator implements Comparator<Squirrel> {


    @Override
    public int compare(Squirrel s1, Squirrel s2) {

        int result = s1.getSpecies().compareTo(s2.getSpecies());
        if(result != 0){
            return result;
        }else {
            return s1.getWeight() - s2.getWeight();
        }
    }

}
