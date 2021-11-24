
import java.util.Arrays;

class BubbleSort {

/* *** ODSATag: BubbleSort *** */
public static <T extends Comparable<T>> void bubbleSort(T[] A) {
    for (int i = 0; i < A.length-1; i++) {
        // Insert i'th record.
        for (int j = 1; j < A.length-i; j++) {
            if (A[j-1].compareTo(A[j]) > 0)
                Util.swap(A, j-1, j);
        }
    }
}
/* *** ODSAendTag: BubbleSort *** */


// Wikipedia article "optimization" to only swap up to the last swap seen
/* *** ODSATag: BubbleSortCheck *** */
static <E extends Comparable<E>> void bubbleSortOpt(E[] A) {
    int n = A.length - 1;
    while (n > 0) {
        int newn = 0;
        for (int i = 0; i < n; i++) {
            // If this pair is out of order.
            if (A[i].compareTo(A[i+1]) > 0) {
                Util.swap(A, i, i+1);
                newn = i;
            }
        }
        n = newn;
    }
}
/* *** ODSAendTag: BubbleSortCheck *** */

}
