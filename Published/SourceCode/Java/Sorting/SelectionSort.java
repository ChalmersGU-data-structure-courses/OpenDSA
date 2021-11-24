
import java.util.Arrays;

class SelectionSort {

static <E extends Comparable<E>> void selectionSort(E[] A) {
    for (int i = 0; i < A.length-1; i++) {       // Select i'th biggest record
        int bigindex = 0;                        // Current biggest index
        for (int j = 1; j < A.length-i; j++) {   // Find the max value
            if (A[j].compareTo(A[bigindex]) > 0) // Found something bigger
                bigindex = j;                    // Remember bigger index
        }
        Util.swap(A, bigindex, A.length-i-1);         // Put it into place
    }
}
}
