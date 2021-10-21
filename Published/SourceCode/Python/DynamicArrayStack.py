
from API import Stack, Iterator

class DynamicArrayStack(Stack):
    def __init__(self):
        self._internalArray = [None]   # Internal array containing the stack elements
        self._stackSize = 0            # Size of stack, and index of the next free slot

    def push(self, x):
        if self._stackSize >= len(self._internalArray):
            self._resizeArray(2 * len(self._internalArray))
        self._internalArray[self._stackSize] = x
        self._stackSize += 1

    def peek(self):
        if not (self._stackSize > 0): raise IndexError("peek from empty stack")
        return self._internalArray[self._stackSize-1]

    def pop(self):
        if not (self._stackSize > 0): raise IndexError("pop from empty stack")
        self._stackSize -= 1
        x = self._internalArray[self._stackSize]
        self._internalArray[self._stackSize] = None   # For garbage collection
        if self._stackSize <= len(self._internalArray) // 3:
            self._resizeArray(len(self._internalArray) // 2)
        return x

    def _resizeArray(self, newCapacity):
        newArray = [None] * newCapacity
        for i in range(self._stackSize):
            newArray[i] = self._internalArray[i]
        self._internalArray = newArray

    def isEmpty(self):
        return self._stackSize == 0

    def size(self):
        return self._stackSize

    def __iter__(self):
        return DynamicArrayStackIterator(self._internalArray, self._stackSize)

# Python does not have internal classes, so we have to make the iterator standalone.
class DynamicArrayStackIterator(Iterator):
    def __init__(self, array, size):
        self._array = array
        self._size = size
        self._index = size

    def __iter__(self):
        return self

    def __next__(self):
        self._index -= 1
        if self._index < 0:
            raise StopIteration
        return self._array[self._index]


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printList(l):
    print(len(l._internalArray), "[", "- " * (len(l._internalArray) - l.size()) + "|",
               " ".join(str(e) for e in l), "]", l.size())

if __name__ == '__main__':
    a = DynamicArrayStack()
    for i in range(23):
        a.push(chr(i+65))
        if a.size() % 5 == 0:
            _printList(a)
    _printList(a)
    while not a.isEmpty():
        assert a.peek() == a.pop(), (a,)
        if a.size() % 3 == 2:
            _printList(a)
    _printList(a)
