/* *** ODSATag: PQsort *** */
static <T extends Comparable<T>> T[] pqsort(T[] array) {
  PriorityQueue<T> pq = new MinHeap<T>();
    // MinHeap is a class that implements the
    // priority queue ADT; we will see how it works
    // in the next section.
  for (T item: array)
    pq.add(item);

  T[] result = new T[array.length];
  for (int i = 0; i < result.length; i++)
    result[i] = pq.removeMin();

  return result;
}
/* *** ODSAendTag: PQsort *** */
