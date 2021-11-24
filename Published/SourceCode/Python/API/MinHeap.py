import sys
import random

class MinHeap:
    _heap: list # The heap array

    # Constructor supporting preloading of heap contents
    def __init__(self, h = None):
        if h is None:
            self._heap = []
        else:
            self._heap = list(h) # Make a copy of h

        self._buildHeap()

    def isEmpty(self):
        """Return true if there are no elements."""

        return self.size() == 0

    def size(self):
        """Return current size of the heap."""

        return len(self._heap)

    def getMin(self):
        """Return smallest item into heap."""

        if self.size() == 0:
            raise IndexError("getMin from empty heap")

        return self._heap[0]

    def add(self, elem):
        """Insert elem into heap."""

        self._heap.append(elem)         # New item starts at end of heap.
        self._siftUp(len(self._heap)-1) # Put the new value in its correct place.

    def removeMin(self):
        """Remove and return minimum value."""

        if len(self._heap) == 0:
            raise IndexError("removeMin from empty heap")

        removed = self._heap[0]
        last = self._heap.pop()     # Find and remove last element

        if len(self._heap) > 0:
            self._heap[0] = last    # Replace root with last element
            self._siftDown(0)       # Put the new root in its correct place.

        return removed

    def __str__(self):
        return str(self._heap)

    def __repr__(self):
        return repr(self._heap)

    def __iter__(self):
        return iter(self._heap)

    # Private helper methods

    def _checkInvariant(self):
        """Check that the invariant holds."""

        heapSize = len(self._heap)
        for i in range(heapSize):
            left = self._getLeftChild(i)
            right = self._getRightChild(i)

            if left < heapSize and self._lessThan(left, i):
                raise AssertionError("Parent (" + i + ") is smaller than its left child: " + self._heap[i] + " < " + self._heap[left]);

            if right < heapSize and self._lessThan(right, i):
                raise AssertionError("Parent (" + i + ") is smaller than its right child: " + self._heap[i] + " < " + self._heap[right]);

    def _isLeaf(self, pos):
        """Return true if pos is a leaf position, false otherwise."""

        return pos >= len(self._heap) // 2

    def _getLeftChild(self, pos):
        """Return the position for the left child of the given node."""

        return 2*pos+1

    def _getRightChild(self, pos):
        """Return the position for the right child of the given node."""

        return 2*pos+2

    def _getParent(self, pos):
        """Return the position for the parent. Returns 0 if we're already at the root."""

        return (pos-1) // 2

    def _swap(self, pos1, pos2):
        """Swap the values in two positions."""

        self._heap[pos1], self._heap[pos2] = self._heap[pos2], self._heap[pos1]

    def _buildHeap(self):
        """Heapify the contents of an array."""

        heapSize = len(self._heap)
        # Loop from heapSize/2-1 down to 0
        for pos in reversed(range(heapSize//2)):
            self._siftDown(pos)

    def _siftDown(self, pos):
        """Sift a value down the tree, return its new position."""

        heapSize = len(self._heap)
        while not self._isLeaf(pos):
            child = self._getLeftChild(pos)
            right = child + 1   # or: self._getRightChild(pos)
            if right < heapSize and self._lessThan(right, child):
                child = right   # 'child' is now the index of the child with smaller value
            if not self._lessThan(child, pos):
                return pos
            self._swap(pos, child)
            pos = child   # Move down one level in the tree.

        return pos

    def _siftUp(self, pos):
        """Sift a value up the tree, return its new position."""

        while pos > 0:
            parent = self._getParent(pos)
            if not self._lessThan(pos, parent):
                return pos
            self._swap(pos, parent)
            pos = parent   # Move up one level in the tree.

        return pos

    def _lessThan(self, i, j):
        # Compare the values in the given positions.

        return self._heap[i] < self._heap[j]

def main():
    """A very simple testing method."""

    if len(sys.argv) > 1:
        h = MinHeap(sys.argv[1:])
        print(h)
        h._checkInvariant()

    else:
        h = MinHeap()
        for i in range(20):
            r = "%d" % random.randint(10, 50)
            h.add(r)
            print("Adding %s  -->  %s" % (r, h))
            h._checkInvariant()

    print()

    while not h.isEmpty():
        x = h.removeMin()
        print("Remove %s  -->  %s" % (x, h))
        h._checkInvariant()

if __name__ == '__main__':
    main()
