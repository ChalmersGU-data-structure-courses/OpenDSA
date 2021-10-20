# /* *** ODSATag: PQsort *** */
def pqsort(array):
  pq = MinHeap()
    # MinHeap is a class that implements the
    # priority queue ADT; we will see how it works
    # in the next section.
  for item in array:
    pq.add(item)

  result = []
  while not pq.isEmpty():
    result.add(pq.removeMin())
    
  return result
# /* *** ODSAendTag: PQsort *** */
