/* *** ODSATag: PriorityQueue *** */
public interface PriorityQueue<Item> { // PriorityQueue class ADT
  // Add element
  void add(Item x);

  // Remove and return smallest element
  Item removeMin();

  // Return smallest element
  Item getMin();

  // Return priority queue size
  int size();

  // Return true if the priority queue is empty
  boolean isEmpty();
}
/* *** ODSAendTag: PriorityQueue *** */
