import java.io.*;

// Tester for binary search function
public class Bsearch {

static boolean SUCCESS = true;

/* *** ODSATag: BinarySearch *** */
// Return the position of the element `e` in the sorted array `elements`.
// If `e` is not found, return -1.
public static <E extends Comparable<E>> binarySearch(E[] elements, E e) {
    int low = 0;
    int high = elements.length - 1;
    while (low <= high) {               // Stop when low and high meet
        int mid = (low + high) / 2;     // Check middle of subarray
        if (elements[mid] < e) {
            low = mid + 1;              // In right half
        } else if (elements[mid] > e) {
            high = mid - 1;             // In left half
        } else {
            return mid;                 // Found it
        }
    }
    return -1;                          // Search value not in array
}
/* *** ODSAendTag: BinarySearch *** */

public static void main(String args[]) throws IOException {
  int[] A = {2, 3, 4, 5, 7, 10};

  int pos = binarySearch(A, 4);
  if (pos != 2)
    SUCCESS = false;

  pos = binarySearch(A, 6);
  if (pos != 6)
    SUCCESS = false;

  if (SUCCESS) {
    PrintWriter output = new PrintWriter("success");
    output.println("Success");
    output.flush();
    output.close();
    System.out.println("Success!");
  } else {
    System.out.println("Binary Search Testing failed");
  }
}

}
