import java.io.*;

// Tester for binary search function
class SequentialSearch {

/* *** ODSATag: Sequential *** */
// Return the position of an element in a list.
// If the element is not found, return -1.
public static<E> int sequentialSearch(E[] elements, E e) {
    for (int i = 0; i < elements.length; i++) { // For each element
        if (elements[i].equals(e))              // if we found it
            return i;                           // return its position
    }
    return -1;                                  // Otherwise, return -1
}
/* *** ODSAendTag: Sequential *** */

    public static void main(String args[]) throws IOException {
        Integer[] A = {2, 3, 4, 5, 7, 10};
        Integer[] searchKeys = {4, 6, 10};
        for (Integer key : searchKeys) {
            int pos = SequentialSearch.sequentialSearch(A, key);
            System.out.println("Search for " + key + " --> position " + pos);
        }
    }
}
