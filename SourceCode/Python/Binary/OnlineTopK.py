# /* *** ODSATag: OnlineTopK *** */
class Top100Transactions:
    # Assume that the Transaction class implements comparisons
    # by comparing the value of the transaction.
    def __init__(self):
        self.pq = MinHeap()

    # Add a new transaction to the priority queue.
    def add(transaction):
        pq.add(transaction)
        # If the priority queue grows to 101 transactions,
        # cut it down to 100 by removing the smallest-valued one.
        if pq.size() > 100:
            pq.removeMin()

    # Return the top 100 transactions.
    def top100():
        return pq.iterator()
# /* *** ODSAendTag: OnlineTopK *** */
