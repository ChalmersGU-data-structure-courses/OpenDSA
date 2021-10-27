
from API import List

class DynamicArrayList(List):
    _minCapacity = 8
    _minLoadFactor = 0.5
    _capacityMultiplier = 1.5

    def __init__(self):
        self._internalArray = [None] * self._minCapacity   # Internal array containing the list elements
        self._listSize = 0                                 # Size of list

    def get(self, i):
        if not (0 <= i < self._listSize): raise IndexError("list index out of range")
        return self._internalArray[i]

    def set(self, i, x):
        if not (0 <= i < self._listSize): raise IndexError("list index out of range")
        old = self._internalArray[i]
        self._internalArray[i] = x
        return old

    def add(self, i, x):
        if not (0 <= i <= self._listSize): raise IndexError("list index out of range")
        if self._listSize >= len(self._internalArray):
            self._resizeArray(len(self._internalArray) * self._capacityMultiplier)
        self._listSize += 1
        for k in reversed(range(i+1, self._listSize)):
            self._internalArray[k] = self._internalArray[k-1]
        self._internalArray[i] = x

    def remove(self, i):
        if not (0 <= i < self._listSize): raise IndexError("list index out of range")
        x = self._internalArray[i]
        for k in range(i+1, self._listSize):
            self._internalArray[k-1] = self._internalArray[k]
        self._listSize -= 1
        self._internalArray[self._listSize] = None   # For garbage collection
        if self._listSize <= len(self._internalArray) * self._minLoadFactor:
            self._resizeArray(len(self._internalArray) / self._capacityMultiplier)
        return x

    def _resizeArray(self, newCapacity):
        if newCapacity < self._minCapacity: return
        newArray = [None] * int(newCapacity)
        for i in range(self._listSize):
            newArray[i] = self._internalArray[i]
        self._internalArray = newArray

    def isEmpty(self):
        return self._listSize == 0

    def size(self):
        return self._listSize

    def __iter__(self):
        for i in range(self._listSize):
            yield self._internalArray[i]


#######################################################################################
## What comes below is purely for debugging and testing purposes - it can be removed ##

def _printList(l):
    print(len(l._internalArray), "[", " ".join(str(e) for e in l), "|",
              "- " * (len(l._internalArray) - l.size()) + "]", l.size())

if __name__ == '__main__':
    a = DynamicArrayList()
    for i in range(20):
        a.add(a.size(), chr(i+65))
        if i % 5 == 0:
            _printList(a)
    _printList(a)
    for i in range(0, a.size(), 2): a.set(i, a.get(i).lower())
    _printList(a)
    for _ in range(4):
        for i in range(a.size()-1, -1, -3): a.remove(i)
        _printList(a)
