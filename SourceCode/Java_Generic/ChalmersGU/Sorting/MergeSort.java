
import java.util.Arrays;

class MergeSort {

/* *** ODSATag: MergeSortMain *** */
public static <E extends Comparable<E>> void mergeSort(E[] A) {
    @SuppressWarnings("unchecked")
    E[] temp = (E[]) new Comparable[A.length];
    mergeSort(A, temp, 0, A.length-1);
}
/* *** ODSAendTag: MergeSortMain *** */

/* *** ODSATag: MergeSort *** */
public static <T extends Comparable<T>> void mergeSort(T[] A, T[] temp, int left, int right) {
    if (left == right)                   // List has one record
        return;
    int mid = (left + right) / 2;        // Select midpoint
    mergeSort(A, temp, left, mid);       // Mergesort first half
    mergeSort(A, temp, mid+1, right);    // Mergesort second half
    for (int i = left; i <= right; i++)  // Copy subarray to temp
        temp[i] = A[i];
    // Do the merge operation back to A
    int i1 = left;
    int i2 = mid + 1;
    for (int curr = left; curr <= right; curr++) {
        if (i1 == mid+1) {               // Left sublist exhausted
            A[curr] = temp[i2];
            i2++;
        } else if (i2 > right) {         // Right sublist exhausted
            A[curr] = temp[i1];
            i1++;
        } else if (temp[i1].compareTo(temp[i2]) <= 0) {  // Get smaller value
            A[curr] = temp[i1];
            i1++;
        } else {
            A[curr] = temp[i2];
            i2++;
        }
    }
}
/* *** ODSAendTag: MergeSort *** */

static int THRESHOLD = 10;

static <T extends Comparable<T>> void insertionSort(T[] A, int left, int right) {
    for (int i=left+1; i<=right; i++)        // Insert i'th record
        for (int j=i; (j>left) && (A[j].compareTo(A[j-1]) < 0); j--)
            Util.swap(A, j, j-1);
}

/* *** ODSATag: MergeSortOpt *** */
public static <T extends Comparable<T>> void mergeSortOpt(T[] A, T[] temp, int left, int right) {
    int i, j, k;
    int mid = (left+right)/2;    // Select the midpoint
    if (left == right)           // List has one record
        return;
    if (mid-left >= THRESHOLD)
        mergeSortOpt(A, temp, left, mid);
    else
        insertionSort(A, left, mid);
    if (right-mid > THRESHOLD)
        mergeSortOpt(A, temp, mid+1, right);
    else
        insertionSort(A, mid+1, right);
    // Do the merge operation.  First, copy 2 halves to temp.
    for (i=left; i<=mid; i++) {
        temp[i] = A[i];
    }
    i = mid+1;
    for (j=right; j>mid; j--) {
        temp[i] = A[j];
        i++;
    }
    // Merge sublists back to array
    i=left; j=right;
    for (k=left; k<=right; k++) {
        if (temp[i].compareTo(temp[j]) <= 0) {
            A[k] = temp[i];
            i++;
        } else {
            A[k] = temp[j];
            j--;
        }
    }
}
/* *** ODSAendTag: MergeSortOpt *** */
}
