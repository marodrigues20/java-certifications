package chapter_9.list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyLinkedList {

    static void main() {

        List<String> linkedList = new LinkedList<>(List.of("Alex", "Bob", "David", "Xaquira"));
        linkedList.addFirst("Olivia");
        linkedList.addLast("Rodrigues");

        // O(n)
        System.out.println("Access by index: " + linkedList.get(2));

        // O(n2) - Not use it. Better do with Iterator
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println("Looping over linkedList: " + linkedList.get(i));
        }

        // O(n)
        Iterator<String> iteratorLinkedList = linkedList.iterator();
        while (iteratorLinkedList.hasNext()) {
            System.out.println("Let's iterate over iteratorLinkedList: " + iteratorLinkedList.next());
        }

        linkedList.sort(Comparator.naturalOrder());

        for (String name : linkedList) {
            System.out.println(name);
        }

        linkedList.stream()
                .forEach(System.out::println);

    }


}
