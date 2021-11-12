/* *** ODSATag: OnlineTopK *** */
class Top100Transactions {
    // Assume that the Transaction class implements Comparable
    // by comparing the value of the transaction.
    private PriorityQueue<Transaction> pq = new MinHeap<>();

    // Add a new transaction to the priority queue.
    public void add(Transaction transaction) {
        pq.add(transaction);
        // If the priority queue grows to 101 transactions,
        // cut it down to 100 by removing the smallest-valued one.
        if (pq.size() > 100)
            pq.removeMin();
    }

    // Return the top 100 transactions.
    public Iterator<Transaction> top100() {
        return pq.iterator();
    }
}
/* *** ODSAendTag: OnlineTopK *** */
