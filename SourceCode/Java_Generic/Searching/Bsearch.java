import java.io.*;

// Tester for binary search function
class BinarySearch {

/* *** ODSATag: BinarySearch *** */
// Return the position of an element in a list.
// If the element is not found, return -1.
public static <E extends Comparable<E>> int binarySearch(E[] elements, E e) {
    int low = 0;
    int high = elements.length - 1;
    while (low <= high) {               // Stop when low and high meet
        int mid = (low + high) / 2;     // Check middle of subarray
        if (elements[mid].compareTo(e) < 0) {
            low = mid + 1;              // In right half
        } else if (elements[mid].compareTo(e) > 0) {
            high = mid - 1;             // In left half
        } else {
            return mid;                 // Found it
        }
    }
    return -1;                          // Search value not in array
}
/* *** ODSAendTag: BinarySearch *** */
}

class Bsearch {
    public static void main(String args[]) throws IOException {
        Integer[] A = {2, 3, 4, 5, 7, 10};
        Integer[] searchKeys = {4, 6, 10};
        for (Integer key : searchKeys) {
            int pos = BinarySearch.binarySearch(A, key);
            System.out.println("Search for " + key + " --> position " + pos);
        }
    }
}
