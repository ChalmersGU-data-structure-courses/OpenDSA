/* *** ODSATag: OnlineTopK *** */
class TopK<Item extends Comparable<Item>> {
    private PriorityQueue<Item> pq;
    private int k;

    public TopK(int k) {
        this.pq = new MinHeap<Item>();
        this.k = k;
    }

    public void add(Item item) {
        pq.add(item);
        if (pq.size() > k)
            pq.removeMin();
    }

    public Item[] topK() {
        Item[] result = (Item[]) new Object[pq.size()];
        int i = 0;
        for (Item item: pq) {
            result[i] = item;
            i++;
        }
        Collections.sort(result);
        return result;
    }
}
/* *** ODSAendTag: OnlineTopK *** */
