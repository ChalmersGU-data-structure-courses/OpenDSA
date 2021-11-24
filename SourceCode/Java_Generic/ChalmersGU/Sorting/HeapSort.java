
import java.util.Arrays;

class HeapSort {

/* *** ODSATag: HeapSort *** */
public static <E extends Comparable<E>> void heapSort(E[] A) {
    buildMaxHeap(A);          // First we turn the given array into a max heap.
    for (int size = A.length; size > 0; size--) {
        removeMax(A, size);   // Place the maximum value at the end of the heap.
    }
}

// Heapify the contents of an array.
static <E extends Comparable<E>> void buildMaxHeap(E[] A) {
    for (int pos = A.length/2-1; pos >= 0; pos--)
        // Sift down all non-leaves in the heap, starting from the lowest level nodes.
        siftDown(A, pos, A.length);
}

// Remove the maximum value from the heap array,
// by moving it to the end of the heap.
static <E extends Comparable<E>> void removeMax(E[] A, int size) {
    Util.swap(A, 0, size-1);       // Make the current last value the new root.
    siftDown(A, 0, size-1);   // Put the new heap root val in its correct place.
}

// Sift a value down the heap.
static <E extends Comparable<E>> void siftDown(E[] A, int pos, int size) {
    while (pos < size/2) {
        int child = 2 * pos + 1;   // The left child.
        int right = child + 1;     // The right child.
        if (right < size && A[right].compareTo(A[child]) > 0)
            child = right;         // 'child' is now the index of the child with larger value
        if (A[child].compareTo(A[pos]) <= 0)
            return;
        Util.swap(A, pos, child);
        pos = child;               // Move down one level in the tree.
    }
}
/* *** ODSAendTag: HeapSort *** */
}
