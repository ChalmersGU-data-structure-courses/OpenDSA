#/* *** ODSATag: Maxheap *** */
# Max-heap implementation
class MaxHeap:
    def __init__(self, h, num, max):
        self.heap = h       # Pointer to the heap array
        self.n = num        # Number of things now in heap
        self.size = max     # Maximum size of the heap
        self.buildheap()

    def heapsize(self):
        return self.n

    def isLeaf(self, pos):
        return self.n//2 <= pos < self.n

    def leftchild(self, pos):
        if pos >= n//2:
            return -1
        else:
            return 2*pos + 1

    def rightchild(self, pos):
        if pos >= (self.n-1)//2:
            return -1
        else:
            return 2*pos + 2

    def parent(self, pos):
        if pos <= 0:
            return -1
        else:
            return (pos-1)//2

    def insert(self, key):
        if self.n >= size:
            raise IndexError("Heap is full")
        curr = self.n
        self.n += 1
        self.heap[curr] = key  # Start at end of heap
        # Now sift up until curr's parent's key > curr's key
        while curr != 0 and self.heap[curr] > self.heap[self.parent(curr)]:
            swap(self.heap, curr, self.parent(curr))
            self.curr = self.parent(curr)

    def buildheap(self):
        for i in range(self.n//2-1, -1, -1):
            self.siftdown(i)

    def siftdown(self, pos):
        if pos < 0 or pos >= n:
            raise IndexError("Illegal position")
        while not self.isLeaf(pos):
            j = self.leftchild(pos)
            if j<(n-1) and self.heap[j] < self.heap[j+1]:
                j += 1  # j is now index of child with greater value
            if self.heap[pos] >= self.heap[j]:
                return
            swap(self.heap, pos, j)
            pos = j  # Move down

    def removemax(self):
        if self.n == 0:
            raise IndexError("Removing from empty heap")
        self.n -= 1
        swap(self.heap, 0, self.n)  # Swap maximum with last value
        self.siftdown(0)            # Put new heap root val in correct place
        return self.heap[n]

    def remove(self, pos):
        if pos < 0 or pos >= self.n:
            raise IndexError("Illegal heap position")
        self.n -= 1
        if pos != self.n:
            swap(self.heap, pos, self.n)  # Swap with last value
            self.update(pos)
        return self.heap[n]

    def modify(self, pos, newVal):
        if pos < 0 or pos >= self.n:
            raise IndexError("Illegal heap position")
        self.heap[pos] = newVal
        self.update(pos)

    def update(self, pos):
        # If it is a big value, push it up
        while pos > 0 and self.heap[pos] > self.heap[self.parent(pos)]:
            swap(self.heap, pos, self.parent(pos))
            pos = self.parent(pos)
        self.siftdown(pos)  # If it is little, push down
#/* *** ODSAendTag: Maxheap *** */
