
from API import List, Iterator

#/* *** ODSATag: DynamicArrayList *** */
#/* *** ODSATag: DynamicArrayListInit *** */
class DynamicArrayList(List):
    def __init__(self, size=0):
        self._internalArray = [None] * (size + 1)
        self._arraySize = size
#/* *** ODSAendTag: DynamicArrayListInit *** */

    def get(self, i):
        if not (0 <= i < self._arraySize):
            raise IndexError("array index out of range")
        return self._internalArray[i]

    def set(self, i, x):
        if not (0 <= i < self._arraySize):
            raise IndexError("array index out of range")
        old = self._internalArray[i]
        self._internalArray[i] = x
        return old

#/* *** ODSATag: DynamicArrayListAdd *** */
    def add(self, i, x):
        if not (0 <= i <= self._arraySize):
            raise IndexError("array index out of range")
        if self._arraySize >= len(self._internalArray):
            self._resizeArray(2 * len(self._internalArray))
        for k in range(self._arraySize, i, -1):
            self._internalArray[k] = self._internalArray[k-1]
        self._internalArray[i] = x
        self._arraySize += 1
#/* *** ODSAendTag: DynamicArrayListAdd *** */

#/* *** ODSATag: DynamicArrayListRemove *** */
    def remove(self, i):
        if self._arraySize == 0:
            raise IndexError("remove from empty array")
        removed = self._internalArray[i]
        self._arraySize -= 1
        for k in range(i, self._arraySize):
            self._internalArray[k] = self._internalArray[k+1]
        # This is to be able to garbage collect the last element:
        self._internalArray[self._arraySize] = None
        if self._arraySize < len(self._internalArray) // 3:
            self._resizeArray(len(self._internalArray) // 2)
        return removed
#/* *** ODSAendTag: DynamicArrayListRemove *** */

#/* *** ODSATag: DynamicArrayResize *** */
    def _resizeArray(self, newCapacity):
        newArray = [None] * newCapacity
        for i in range(self._arraySize):
            newArray[i] = self._internalArray[i]
        self._internalArray = newArray
#/* *** ODSAendTag: DynamicArrayResize *** */

    def isEmpty(self):
        return self._arraySize == 0

    def size(self):
        return self._arraySize

#/* *** ODSATag: DynamicArrayListIterator *** */
    def iterator(self):
        return DynamicArrayListIterator(self._internalArray, self._arraySize)

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
    print(" ".join(a.iterator()))
    for i in range(0, a.size(), 2): a.set(i, a.get(i) + a.get(i))
    print(" ".join(a.iterator()))
    i = 0
    while i < a.size():
        a.remove(i)
        i += 3
    print(" ".join(a.get(i) for i in range(a.size())))
