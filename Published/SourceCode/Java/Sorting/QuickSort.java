
import java.util.Arrays;

class QuickSort {

/* Warning: Partition is sensitive. If we don't make the right
   position actually cross the left, then it seems hard to get things
   to work right when there is only one element in the partition
   (i.e., a list of 2 elements). */
static <E extends Comparable<E>> int partition(E[] A, int left, int right, int pivot) {
    Util.swap(A, left, pivot);   // Put pivot at the leftmost index
    pivot = left;
    left++;                 // Start partitioning from the element after the pivot

    E pivotValue = A[pivot];
    while (true) {
        // Move `left` right as far as possible. Stop if equal to pivot!
        // Also stop if `left` moves past `right` – this is important,
        // so that `left` stops if it moves past the end of the array.
        while (left <= right && A[left].compareTo(pivotValue) < 0) 
            left++;

        // Move `right` left as far as possible. Stop if equal to pivot!
        // Also stop if `right` moves all the way left to `left`,
        // see above for why.
        while (left <= right && A[right].compareTo(pivotValue) > 0) 
            right--;

        // Stop here if `left` and `right` passed each other.
        if (left > right)
            break;

        // Otherwise, swap the elements and move `left` and `right` on by 1.
        Util.swap(A, left, right);
        left++; right--;
    }

    Util.swap(A, pivot, right);   // Finally, move the pivot into place
    return right;            // Return the position of the pivot
}

static <E extends Comparable<E>> int findPivot(E[] A, int i, int j) {
    // Not-so-good pivot selection: always choose the middle element.
    return (i + j) / 2;
}

public static <E extends Comparable<E>> void quickSort(E[] A, int left, int right) {
    if (left >= right)                          // Base case: Subarray length is <= 1
        return;
    int pivot = findPivot(A, left, right);      // Pick a pivot index
    pivot = partition(A, left, right, pivot);   // Partition the subarray; update pivot with its new position
    quickSort(A, left, pivot-1);                // Sort left partition
    quickSort(A, pivot+1, right);               // Sort right partition
}

public static <E extends Comparable<E>> void quickSort(E[] A) {
    quickSort(A, 0, A.length-1);
}

// Insertion sort used by optimized quicksort
// Instead of swapping, "shift" the values down the array
static <E extends Comparable<E>> void insertionSortShift(E[] A) {
    for (int i=1; i<A.length; i++) { // Insert i'th record
        int j;
        E temp = A[i];
        for (j=i; (j>0) && (temp.compareTo(A[j-1]) < 0); j--)
            A[j] = A[j-1];
        A[j] = temp;
    }
}

static int MAXSTACKSIZE = 100;
static int THRESHOLD = 10;

// Optimized Quicksort: Not recursive, and uses Inssort for small lists
public static <E extends Comparable<E>> void quickSortOpt(E[] A, int oi, int oj) { // Quicksort
    int[] Stack = new int[MAXSTACKSIZE]; // Stack for array bounds
    int listsize = oj-oi+1;
    int top = -1;
    E pivot;
    int pivotindex, l, r;

    Stack[++top] = oi;  // Initialize stack
    Stack[++top] = oj;

    while (top > 0) {   // While there are unprocessed subarrays
        // Pop Stack
        int j = Stack[top--];
        int i = Stack[top--];

        // Findpivot
        pivotindex = (i+j)/2;
        pivot = A[pivotindex];
        Util.swap(A, pivotindex, j); // Stick pivot at end

        // Partition
        l = i-1;
        r = j;
        do {
            while (A[++l].compareTo(pivot)<0);
            while ((r!=0) && (A[--r].compareTo(pivot)>0));
            Util.swap(A, l, r);
        } while (l < r);
        Util.swap(A, l, r);  // Undo final swap
        Util.swap(A, l, j);  // Put pivot value in place

        // Put new subarrays onto Stack if they are small
        if ((l-i) > THRESHOLD) {   // Left partition
            Stack[++top] = i;
            Stack[++top] = l-1;
        }
        if ((j-l) > THRESHOLD) {   // Right partition
            Stack[++top] = l+1;
            Stack[++top] = j;
        }
    }
    insertionSortShift(A);             // Final Insertion Sort
}

}
