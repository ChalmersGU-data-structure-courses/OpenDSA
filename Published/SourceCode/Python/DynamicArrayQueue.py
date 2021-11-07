
from API import Queue

class DynamicArrayQueue(Queue):
    _minCapacity = 8            # Minimum capacity of internalArray
    _minLoadFactor = 0.5        # Must be smaller than 1/CapacityMultiplier
    _capacityMultiplier = 1.5   # Factor to grow/shrink the capacity

    def __init__(self):
        self._internalArray = [None] * self._minCapacity   # Internal array containing the queue elements
        self._queueSize = 0                                # Size of queue, and index of the next free slot
        self._front = 0                                    # Index of front element
        self._rear = -1                                    # Index of rear element

    def enqueue(self, x):
        if self._queueSize >= len(self._internalArray):
            self._resizeArray(len(self._internalArray) * self._capacityMultiplier)
        self._rear = (self._rear + 1) % len(self._internalArray)   # Circular increment
        self._internalArray[self._rear] = x
        self._queueSize += 1

    def peek(self):
        if not (self._queueSize > 0): raise IndexError("peek from empty queue")
        return self._internalArray[self._front]

    def dequeue(self):
        if not (self._queueSize > 0): raise IndexError("dequeue from empty queue")
        self._queueSize -= 1
        x = self._internalArray[self._front]
        self._internalArray[self._front] = None   # For garbage collection
        self._front = (self._front + 1) % len(self._internalArray)   # Circular increment
        if self._queueSize <= len(self._internalArray) * self._minLoadFactor:
            self._resizeArray(len(self._internalArray) / self._capacityMultiplier)
        return x

    def _resizeArray(self, newCapacity):
        if newCapacity < self._minCapacity: return
        newArray = [None] * int(newCapacity)
        for i in range(self._queueSize):
            newArray[i] = self._internalArray[(i + self._front) % len(self._internalArray)]
        self._internalArray = newArray
        self._front = 0
        self._rear = self._queueSize-1

    def isEmpty(self):
        return self._queueSize == 0

    def size(self):
        return self._queueSize

    def __iter__(self):
        for i in range(self._front, self._front + self._queueSize):
            yield self._internalArray[i % len(self._internalArray)]


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printList(l):
    print(len(l._internalArray), ":", l._front, "[",
          " ".join("-" if e is None else str(e) for e in l._internalArray), "]",
          l._rear, " ... ", " ".join(str(e) for e in l), "|", l.size())

if __name__ == '__main__':
    a = DynamicArrayQueue()
    for i in range(23):
        a.enqueue(chr(i+65))
        a.enqueue(chr(i+97))
        a.dequeue()
        if a.size() % 5 == 0: _printList(a)
    _printList(a)
    while not a.isEmpty():
        assert a.peek() == a.dequeue(), (a,)
        if a.size() % 3 == 2: _printList(a)
    _printList(a)
