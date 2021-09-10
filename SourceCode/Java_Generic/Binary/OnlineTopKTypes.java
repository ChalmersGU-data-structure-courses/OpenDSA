/* *** ODSATag: OnlineTopKTypes *** */
class TopK<Item extends Comparable<Item>> {
    // Initialise the data structure, choosing the value for k.
    public TopK(int k) { ... }

    // Add an item to the data structure. O(log k).
    public void add(Item item) { ... }

    // Return the top k items, in ascending order. O(k log k).
    public Item[] topK() { ... }
}
/* *** ODSAendTag: OnlineTopKTypes *** */
