
// Note: This algorithm uses the MinHeap data structure,
// so API.java and MinHeap.java have to be in the same directory.

import java.util.Arrays;

class PQSort {

/* *** ODSATag: PQSort *** */
public static <E extends Comparable<E>> void pqSort(E[] array) {
    // MinHeap is a class that implements the priority queue ADT;
    // we will see how it works in the next section.
    PriorityQueue<E> pq = new MinHeap<E>();
    for (E item : array)
        pq.add(item);
    for (int i = 0; i < array.length; i++)
        array[i] = pq.removeMin();
}
/* *** ODSAendTag: PQSort *** */

}
