@SuppressWarnings("unchecked") // Generic array allocation
static Boolean sorttest(int[] B) {
  int i;
  Integer[] A = new Integer[B.length];
  for (i=0; i<B.length; i++) {
    A[i] = new Integer(B[i]);
  }
  heapsort(A);
  if (!checkorder(A)) { return false; }
  return true;
}

/* *** ODSATag: HeapSort *** */
static <T extends Comparable<T>> void heapSort(T[] A) {
    // The heap constructor invokes the buildheap method
    MaxHeap H = new MaxHeap(A, A.length, A.length);
    // Now sort
    for (int i = 0; i < A.length; i++) {
        H.removemax(); // Removemax places max at end of heap
    }
}
/* *** ODSAendTag: HeapSort *** */
