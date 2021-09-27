
from API import List, Iterator

#/* *** ODSATag: DynamicArrayList *** */
#/* *** ODSATag: DynamicArrayListInit *** */
class DynamicArrayList(List):
    def __init__(self):
        self._internalArray = [None]
        self._listSize = 0
#/* *** ODSAendTag: DynamicArrayListInit *** */

    def get(self, i):
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        return self._internalArray[i]

    def set(self, i, x):
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        old = self._internalArray[i]
        self._internalArray[i] = x
        return old

#/* *** ODSATag: DynamicArrayListAdd *** */
    def add(self, i, x):
        if not (0 <= i <= self._listSize): raise IndexError("array index out of range")
        if self._listSize >= len(self._internalArray):
            self._resizeArray(2 * len(self._internalArray))
        self._listSize += 1
        for k in reversed(range(i+1, self._listSize)):
            self._internalArray[k] = self._internalArray[k-1]
        self._internalArray[i] = x
#/* *** ODSAendTag: DynamicArrayListAdd *** */

#/* *** ODSATag: DynamicArrayListRemove *** */
    def remove(self, i):
        if not (self._listSize > 0):      raise IndexError("remove from empty array")
        if not (0 <= i < self._listSize): raise IndexError("array index out of range")
        x = self._internalArray[i]
        for k in range(i+1, self._listSize):
            self._internalArray[k-1] = self._internalArray[k]
        self._listSize -= 1
        self._internalArray[self._listSize] = None   # For garbage collection
        if self._listSize < len(self._internalArray) // 3:
            self._resizeArray(len(self._internalArray) // 2)
        return x
#/* *** ODSAendTag: DynamicArrayListRemove *** */

#/* *** ODSATag: DynamicArrayListResize *** */
    def _resizeArray(self, newCapacity):
        newArray = [None] * newCapacity
        for i in range(self._listSize):
            newArray[i] = self._internalArray[i]
        self._internalArray = newArray
#/* *** ODSAendTag: DynamicArrayListResize *** */

    def isEmpty(self):
        return self._listSize == 0

    def size(self):
        return self._listSize

#/* *** ODSATag: DynamicArrayListIterator *** */
    def __iter__(self):
        return DynamicArrayListIterator(self._internalArray, self._listSize)

# Python does not have internal classes, so we have to make the iterator standalone.
class DynamicArrayListIterator(Iterator):
    def __init__(self, array, size):
        self._array = array
        self._size = size
        self._index = -1

    def __iter__(self):
        return self

    def __next__(self):
        self._index += 1
        if self._index >= self._size:
            raise StopIteration
        return self._array[self._index]
#/* *** ODSAendTag: DynamicArrayListIterator *** */
#/* *** ODSAendTag: DynamicArrayList *** */


if __name__ == '__main__':
    a = DynamicArrayList()
    for i in range(25): a.add(a.size(), chr(i+65))
    print(" ".join(a))
    for i in range(0, a.size(), 2): a.set(i, a.get(i) + a.get(i))
    print(" ".join(iter(a)))
    i = 0
    while i < a.size():
        a.remove(i)
        i += 3
    print(" ".join(a.get(i) for i in range(a.size())))
