
from MinHeap import MinHeap

# /* *** ODSATag: PQSort *** */
def pqSort(array):
    pq = MinHeap()
    # MinHeap is a class that implements the priority queue ADT;
    # we will see how it works in the next section.
    for item in array:
        pq.add(item)
    for i in range(len(array)):
        array[i] = pq.removeMin()
# /* *** ODSAendTag: PQSort *** */


if __name__ == '__main__':
    import sys
    arr = sys.argv[1:]
    if not arr:
        arr = [3, 6, 9, 2, 8, 4, 5, 1, 7]
    pqSort(arr)
    print(arr)

