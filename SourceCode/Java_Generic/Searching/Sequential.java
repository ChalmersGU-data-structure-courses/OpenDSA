import java.io.*;

// Tester for binary search function
public class Sequential {

static boolean SUCCESS = true;

/* *** ODSATag: Sequential *** */
// Return the position of the element `e` in array `elements`.
// If `e` is not found, return -1.
static E sequentialSearch(E[] elements, E e) {
    for (int i = 0; i < elements.length; i++) { // For each element
        if (elements[i].equals(e)) {            // if we found it
            return i;                           // return this position
        }
    }
    return -1;                                  // Otherwise, return -1
}
/* *** ODSAendTag: Sequential *** */

public static void main(String args[]) throws IOException {
  int[] A = {2, 3, 4, 5, 7, 10};

  int pos = sequential(A, 4);
  if (pos != 2) {
    SUCCESS = false;
  }

  pos = sequential(A, 6);
  if (pos != 6) {
    SUCCESS = false;
  }

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
