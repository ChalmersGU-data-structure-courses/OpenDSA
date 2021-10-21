
from API import Queue, Iterator

class DynamicArrayQueue(Queue):
    def __init__(self):
        self._internalArray = [None]   # Internal array containing the queue elements
        self._queueSize = 0            # Size of queue, and index of the next free slot
        self._front = 0                # Index of front element
        self._rear = -1                # Index of rear element

    def enqueue(self, x):
        if self._queueSize >= len(self._internalArray):
            self._resizeArray(2 * len(self._internalArray))
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
        if self._queueSize <= len(self._internalArray) // 3:
            self._resizeArray(len(self._internalArray) // 2)
        return x

    def _resizeArray(self, newCapacity):
        newArray = [None] * newCapacity
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
        return DynamicArrayQueueIterator(self._internalArray, self._front, self._queueSize)

# Python does not have internal classes, so we have to make the iterator standalone.
class DynamicArrayQueueIterator(Iterator):
    def __init__(self, array, front, size):
        self._array = array
        self._front = front
        self._size = size
        self._index = -1

    def __iter__(self):
        return self

    def __next__(self):
        self._index += 1
        if self._index >= self._size:
            raise StopIteration
        return self._array[(self._index + self._front) % len(self._array)]


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
